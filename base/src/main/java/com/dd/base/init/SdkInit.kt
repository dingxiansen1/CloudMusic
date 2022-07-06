package com.dd.base.init

import android.content.Context
import com.dd.base.BuildConfig
import com.dd.base.utils.DataStoreUtils
import com.dd.base.utils.log.LogUtils

object SdkInit {

    fun init(context: Context){

        LogUtils.setDebug(BuildConfig.DEBUG)
        //DataStore初始化
        DataStoreUtils.init(context)
    }
}