package com.dd.cloudmusic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.dd.base.utils.Window
import com.dd.cloudmusic.main.MainPage
import com.dd.cloudmusic.splash.SplashPage
import com.dd.cloudmusic.theme.ComposeAppTheme
import com.dd.cloudmusic.theme.Themem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window =getWindow()
        setContent {
            ComposeAppTheme(themeType = Themem.themeTypeState.value) {
                //是否闪屏页
                var isSplash by remember { mutableStateOf(true) }
                if (isSplash) {

                    Window.hideSystemUI(window)
                    SplashPage(window) { isSplash = false }
                } else {
                    Window.showSystemUI(window)
                    MainPage()
                }
            }
        }
    }
}
