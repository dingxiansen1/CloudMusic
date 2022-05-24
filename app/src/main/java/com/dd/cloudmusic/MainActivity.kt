package com.dd.cloudmusic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.dd.cloudmusic.theme.ComposeAppTheme
import com.dd.cloudmusic.theme.Themem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashPage()
        }
    }
}

@Composable
fun SplashPage() {
    ComposeAppTheme(themeType = Themem.themeTypeState.value) {
      Box{
           AsyncImage(
               model = "https://gimg2.baidu.com/image_search/src=http%3A%" +
                       "2F%2Fhbimg.b0.upaiyun.com%2F35a2991dd120fafe7a553e86" +
                       "a4a5a021813bbfea3e4cb-4F1icm_fw658&refer=http%3A%2F%2" +
                       "Fhbimg.b0.upaiyun.com&app=2002&size=f9999,10000&q=a8" +
                       "0&n=0&g=0n&fmt=auto?sec=1655963689&t=af4827c00636bc94" +
                       "f436f901520df92c",
               contentDescription = ""
           )
       }
    }
}