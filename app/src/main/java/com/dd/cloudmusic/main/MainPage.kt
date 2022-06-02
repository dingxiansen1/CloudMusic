package com.dd.cloudmusic.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.navigation.NavHostController
import com.dd.base.utils.wdp
import com.dd.cloudmusic.main.drawer.MainDrawerPage
import com.dd.cloudmusic.main.find.FindPage
import com.dd.cloudmusic.main.home.HomePage
import com.dd.cloudmusic.main.mine.MinePage
import com.dd.cloudmusic.theme.ComposeAppTheme
import com.dd.cloudmusic.theme.AppTheme
import com.dd.cloudmusic.theme.Themem
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun MainPage(navCtrl: NavHostController) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("首页", "歌手", "我的")
    ComposeAppTheme(themeType = Themem.themeTypeState.value) {
        Scaffold(
            scaffoldState = scaffoldState,
            drawerContent = {
                MainDrawerPage(scaffoldState, scope)
            },
            //抽屉手势关闭，默认开启（开始后滑动屏幕打开抽屉）
            drawerGesturesEnabled = false,
            bottomBar = {
                BottomNavigation(
                    modifier  =Modifier.fillMaxWidth().height(150.wdp),
                    backgroundColor = AppTheme.colors.background
                ) {
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
                            selectedContentColor = AppTheme.colors.error,
                            unselectedContentColor = AppTheme.colors.textPrimary,
                            selected = selectedItem == index,
                            onClick = { selectedItem = index }
                        )
                    }
                }
            },
        ) {
            // 此处需要编写主界面
            when (selectedItem) {
                0 -> HomePage(navCtrl, scaffoldState = scaffoldState, scope = scope)
                1 -> FindPage(navCtrl, scaffoldState = scaffoldState, scope = scope)
                else -> MinePage(navCtrl, scaffoldState = scaffoldState, scope = scope)
            }
        }
    }
}