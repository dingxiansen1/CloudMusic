# 1.查看已连接的设备列表：

```
adb devices
```

# 2.开启或关闭ADB服务：

```
开启：adb start-server

结束：adb kill-server
```

# 3.连接设备

```
连接设备：adb connect 192.168.133.132
断开设备：adb disconnect 192.168.133.132
```

如果是USB连接，直接会连接ADB，如果是想通过网络连接（有线或者无线）,则需要在 同一个局域网 ，通过 IP 连接。上
面192.168.1.61替换成想要连接设备的IP即可

# 4.安装、卸载软件包

```
安装软件：adb install -r (APK路径)
卸载软件：adb uninstall （apk包名）
```

# 5.获取软件包名 

```
列出手机装的所有app的包名： adb shell pm list packages
列出系统应用的所有包名： adb shell pm list packages -s
列出除了系统应用的第三方应用包名： adb shell pm list packages -3
```

# **6.清除应用数据与缓存:** 

```
adb shell pm clear （ apk包名）
```

# 7.启动、停止应用

```
 启动：adb shell am start 包名/Activity名

 停止：adb shell am force-stop （apk包名）
```

