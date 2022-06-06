package com.dd.base.init

import android.content.Context
import com.dd.base.BuildConfig
import com.dd.base.utils.log.LogUtils
import com.tencent.mmkv.MMKV

object SdkInit {

    fun init(context: Context){

        LogUtils.setDebug(BuildConfig.DEBUG)
        //MMKV初始化
        MMKV.initialize(context)
    }
}