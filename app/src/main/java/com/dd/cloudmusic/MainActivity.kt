package com.dd.cloudmusic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.dd.base.utils.WindowUtils
import com.dd.cloudmusic.navigator.NavController
import com.dd.cloudmusic.splash.SplashPage
import com.dd.cloudmusic.splash.SplashViewModel
import com.dd.cloudmusic.theme.ComposeAppTheme
import com.dd.cloudmusic.theme.Themem
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化window工具类
        WindowUtils.Init(this)
        setContent {
            val splashViewModel : SplashViewModel = hiltViewModel()
            splashViewModel.getData()
            ComposeAppTheme(themeType = Themem.themeTypeState.value) {
                //是否闪屏页
                var isSplash by remember { mutableStateOf(true) }
                if (isSplash) {
                    WindowUtils.hideSystemUI()
                    SplashPage() { isSplash = false }
                } else {
                    WindowUtils.showSystemUI()
                    NavController()
                }
            }
        }
    }
}
