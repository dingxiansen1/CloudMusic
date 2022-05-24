package com.dd.base.utils

import android.view.Window
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

object Window {
    // 全屏隐藏系统栏，如：你看视频或者玩游戏的时候，就可以通过此种方式，体验是一样的
    fun hideSystemUI(window: Window) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        // 如果你在Composable里面，可以参考rememberSystemUiController() 一样使用LocalView.current也可以
        WindowInsetsControllerCompat(window, window.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            // 修改行为
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    // 从全屏隐藏状态下，恢复系统栏的显示
    fun showSystemUI(window: Window) {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        WindowInsetsControllerCompat(window, window.decorView).show(WindowInsetsCompat.Type.systemBars())
    }
}