package com.dd.cloudmusic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dd.base.PaletteSelectorDialog
import com.dd.cloudmusic.theme.ComposeAppTheme
import com.dd.cloudmusic.theme.Themem
import com.dd.composeapp.ui.theme.themeColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme(themeType = Themem.themeTypeState.value) {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    var clickPalette by remember { mutableStateOf(false) }
    val themeIndex by remember { Themem.themeIndex }
    Text(text = "Hello $name!",
        Modifier
            .clickable {
                clickPalette = true;
            }
            .background(ComposeAppTheme.colors.themeUi))
    if (clickPalette) {
        PaletteSelectorDialog(
            initKey = Themem.getTheme(),
            onDismiss ={
                clickPalette = false
            },
            onSelectItem = {
                Themem.setTheme(it)
                Themem.selectTheme.value= themeColors[it]
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}