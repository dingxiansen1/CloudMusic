# Livedata学习:

## 1. LiveData 如何感知生命周期的变化？

1. Jetpack 引入了 Lifecycle，让任何组件都能方便地感知界面生命周期的变化。只需实现 LifecycleEventObserver 接口并注册给生命周期对象即可。
2. LiveData 的数据观察者在内部被包装成另一个对象（实现了 LifecycleEventObserver 接口），它同时具备了数据观察能力和生命周期观察能力。

## 2. LiveData 是如何避免内存泄漏的？

1. LiveData 的数据观察者通常是匿名内部类，它持有界面的引用，可能造成内存泄漏。
2. LiveData 内部会将数据观察者进行封装，使其具备生命周期感知能力。当生命周期状态为 DESTROYED 时，自动移除观察者。

内存泄漏是因为**长生命周期的对象持有了短生命周期对象，阻碍了其被回收。**

Observer 作为界面的匿名内部类，它会持有界面的引用，同时 Observer 被 LiveData 持有，LivData 被 ViewModel 持有，而 ViewModel 的生命周期比 Activity 长。（为啥比它长，可以点击[这里](https://juejin.cn/post/6844904176296673287)）。

最终的持有链如下：NonConfigurationInstances 持有 ViewModelStore 持有 ViewModel 持有 LiveData 持有 Observer 持有 Activity。

所以得在界面生命周期结束的时候移除 Observer，这件事情，LiveData 帮我们做了。

```
class LifecycleBoundObserver extends ObserverWrapper 
    implements LifecycleEventObserver {
    final LifecycleOwner mOwner;

    LifecycleBoundObserver(LifecycleOwner owner, Observer<? super T> observer) {
        super(observer);
        mOwner = owner;
    }

    @Override
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        // 获取当前生命周期
        Lifecycle.State currentState = mOwner.getLifecycle().getCurrentState();
        // 若生命周期为 DESTROYED 则移除数据观察者并返回
        if (currentState == DESTROYED) {
            removeObserver(mObserver);
            return
        }
        ...
    }
    ...
}

```

## 3. LiveData 是粘性的吗？若是，它是怎么做到的？

1. LiveData 的值被存储在内部的字段中，直到有更新的值覆盖，所以值是持久的。
2. 两种场景下 LiveData 会将存储的值分发给观察者。一是值被更新，此时会遍历所有观察者并分发之。二是新增观察者或观察者生命周期发生变化（至少为 STARTED），此时只会给单个观察者分发值。
3. LiveData 的观察者会维护一个“值的版本号”，用于判断上次分发的值是否是最新值。该值的初始值是-1，每次更新 LiveData 值都会让版本号自增。
4. LiveData 并不会无条件地将值分发给观察者，在分发之前会经历三道坎：1. 数据观察者是否活跃。2. 数据观察者绑定的生命周期组件是否活跃。3. 数据观察者的版本号是否是最新的。
5. “新观察者”被“老值”通知的现象叫“粘性”。因为新观察者的版本号总是小于最新版号，且添加观察者时会触发一次老值的分发。

```
public abstract class LiveData<T> {
    // 存储数据的字段
    private volatile Object mData;
    // 值版本号
    private int mVersion;
    // 更新值
    protected void setValue(T value) {
        assertMainThread("setValue");
        // 版本号自增
        mVersion++;
        // 存储值
        mData = value;
        // 分发值
        dispatchingValue(null);
    }
}
```

setValue() 是更新 LiveData 值时必然会调用的一个方法，即使是通过 postValue() 更新值，最终也会走这个方法。

LiveData 持有一个版本号字段，用于标识“值的版本”，就像软件版本号一样，这个数字用于判断“当前值是否是最新的”，若版本号小于最新版本号，则表示当前值需要更新。

LiveData 用一个 Object 字段`mData`存储了“值”。所以这个值会一直存在，直到被更新的值覆盖。

LiveData 分发值即是通知数据观察者：

```
public abstract class LiveData<T> {
    // 用键值对方式持有一组数据观察者
    private SafeIterableMap<Observer<? super T>, ObserverWrapper> mObservers =
            new SafeIterableMap<>();
    void dispatchingValue(ObserverWrapper initiator) {
            ...
            // 指定分发给单个数据观察者
            if (initiator != null) {
                considerNotify(initiator);
                initiator = null;
            } 
            // 遍历所有数据观察者分发值
            else {
                for (Iterator<Map.Entry<Observer<? super T>, ObserverWrapper>> iterator =
                        mObservers.iteratorWithAdditions(); iterator.hasNext(); ) {
                    considerNotify(iterator.next().getValue());
                }
            }
            ...
    }
    
    // 真正地分发值
    private void considerNotify(ObserverWrapper observer) {
        // 1. 若观察者不活跃则不分发给它
        if (!observer.mActive) {
            return;
        }
        // 2. 根据观察者绑定的生命周期再次判断它是否活跃，若不活跃则不分发给它
        if (!observer.shouldBeActive()) {
            observer.activeStateChanged(false);
            return;
        }
        // 3. 若值已经是最新版本，则不分发
        if (observer.mLastVersion >= mVersion) {
            return;
        }
        // 更新观察者的最新版本号
        observer.mLastVersion = mVersion;
        // 真正地通知观察者
        observer.mObserver.onChanged((T) mData);
    }

}

```

分发值有两种情况：“分发给单个观察者”和“分发给所有观察者”。当 LiveData 值更新时，需分发给所有观察者。

所有的观察者被存在一个 Map 结构中，分发的方式是通过遍历 Map 并逐个调用`considerNotify()`。在这个方法中需要跨过三道坎，才能真正地将值分发给数据观察者，分别是：

1. 数据观察者是否活跃。
2. 数据观察者绑定的生命周期组件是否活跃。
3. 数据观察者的版本号是否是最新的。

跨过三道坎后，会将最新的版本号存储在观察者的 mLastVersion 字段中，即版本号除了保存在`LiveData.mVersion`，还会在每个观察者中保存一个副本`mLastVersion`，最后才将之前暂存的`mData`的值分发给数据观察者。

每个数据观察者都和一个组件的生命周期对象绑定（见第一节），当组件生命周期发生变化时，会尝试将最新值分发给该数据观察者。

总结一下，LiveData 有两次机会通知观察者，与之对应的有两种分发值的方式：

1. 当值更新时，遍历所有观察者将最新值分发给它们。
2. 当与观察者绑定组件的生命周期发生变化时，将最新的值分发给指定观察者。

假设这样一种场景：*LiveData 的值被更新了一次，随后它被添加了一个新的数据观察者，与之绑定组件的生命周期也正好发生了变化（变化到RESUMED），即数据更新在添加观察者之前，此时更新值会被分发到新的观察者吗？*

## 4. 粘性的 LiveData 会造成什么问题？怎么解决？

#### 解决方案一：带消费记录的值

在值的外面套一层，新增一个标记位标识是否被处理过。

#### 解决方案二：带有最新版本号的观察者

通知观察者前需要跨过三道坎，其中有一道坎是版本号的比对。若新建的观察者版本号小于最新版本号，则表示观察者落后了，需要将最新值分发给它。

LiveData 源码中，新建观察者的版本号总是 -1。

```
// 观察者包装类型
private abstract class ObserverWrapper {
    // 当前观察者最新值版本号，初始值为 -1
    int mLastVersion = START_VERSION;
    ...
}
```

若能够让新建观察者的版本号被最新版本号赋值，那版本号对比的那道坎就过不了，新值就无法分发到新建观察者。

所以得通过反射修改 mLastVersion 字段。

### 解决方案三：SingleLiveEvent

这是谷歌给出的一个解决方案

```
public class SingleLiveEvent<T> extends MutableLiveData<T> {
    // 标志位，用于表达值是否被消费
    private final AtomicBoolean mPending = new AtomicBoolean(false);

    public void observe(LifecycleOwner owner, final Observer<T> observer) {
        // 中间观察者
        super.observe(owner, new Observer<T>() {
            @Override
            public void onChanged(@Nullable T t) {
                // 只有当值未被消费过时，才通知下游观察者
                if (mPending.compareAndSet(true, false)) {
                    observer.onChanged(t);
                }
            }
        });
    }

    public void setValue(@Nullable T t) {
        // 当值更新时，置标志位为 true
        mPending.set(true);
        super.setValue(t);
    }

    public void call() {
        setValue(null);
    }
}

```

专门设立一个 LiveData，它不具备粘性。它通过新增的“中间观察者”，拦截上游数据变化，然后在转发给下游。拦截之后通常可以做一点手脚，比如增加一个标记位`mPending`是否消费过的判断，若消费过则不转发给下游。

在数据驱动的 App 界面下，存在两种值：1. **非暂态数据** 2. **暂态数据**

demo 中用于提示“购物车已满”的数据就是“暂态数据”，这种数据是一次性的，转瞬即逝的，可以消费一次就扔掉。

demo 中购物车中的商品列表就是“非暂态数据”，它的生命周期要比暂态数据长一点，在购物车界面和结算界面存活的期间都应该能被重复消费。

SingleLiveEvent 的设计正是基于对数据的这种分类方法，即暂态数据使用 SingleLiveEvent，非暂态数据使用常规的 LiveData。

#### 解决方案四：Kotlin Flow

## 5. 什么情况下 LiveData 会丢失数据？

在高频数据更新的场景下使用 LiveData.postValue() 时，会造成数据丢失。因为“设值”和“分发值”是分开执行的，之间存在延迟。值先被缓存在变量中，再向主线程抛一个分发值的任务。若在这延迟之间再一次调用 postValue()，则变量中缓存的值被更新，之前的值在没有被分发之前就被擦除了。

## 6. 在 Fragment 中使用 LiveData 需注意些什么？

在 Fragment 中观察 LiveData 时使用`viewLifecycleOwner`而不是`this`。因为 Fragment 和 其中的 View 生命周期不完全一致。LiveData 内部判定生命周期为 DESTROYED 时，才会移除数据观察者。存在一种情况，当 Fragment 之间切换时，被替换的 Fragment 不执行 onDestroy()，当它再次展示时会再次订阅 LiveData，于是乎就多出一个订阅者。	

