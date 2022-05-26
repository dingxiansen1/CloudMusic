package com.dd.cloudmusic.main

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.dd.cloudmusic.find.FindPage
import com.dd.cloudmusic.home.HomePage
import com.dd.cloudmusic.mine.MinePage
import com.dd.cloudmusic.theme.ComposeAppTheme
import com.dd.cloudmusic.theme.Themem

@Composable
fun MainPage(navCtrl: NavHostController) {
    val scaffoldState = rememberScaffoldState()
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("首页", "歌手", "我的")
    ComposeAppTheme(themeType = Themem.themeTypeState.value) {
        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = {
                BottomNavigation(Modifier.background(ComposeAppTheme.colors.background)) {
                    BottomNavigation {
                        items.forEachIndexed { index, item ->
                            BottomNavigationItem(
                                icon = {
                                    when (index) {
                                        0 -> Icon(Icons.Filled.Home, contentDescription = null)
                                        1 -> Icon(Icons.Filled.Favorite, contentDescription = null)
                                        else -> Icon(Icons.Filled.Person, contentDescription = null)
                                    }
                                },
                                label = { Text(item) },
                                selected = selectedItem == index,
                                onClick = { selectedItem = index }
                            )
                        }
                    }
                }
            },
        ) {
            // 此处需要编写主界面
            when (selectedItem) {
                0 -> HomePage(navCtrl)
                1 -> FindPage(navCtrl)
                else -> MinePage(navCtrl)
            }
        }
    }
}