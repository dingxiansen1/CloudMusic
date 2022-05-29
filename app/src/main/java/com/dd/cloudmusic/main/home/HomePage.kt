package com.dd.cloudmusic.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dd.base.BaseApp
import com.dd.base.ext.showToast
import com.dd.base.widgets.RefreshList
import com.dd.cloudmusic.R
import com.dd.cloudmusic.main.drawer.MainDrawerPage
import com.dd.cloudmusic.theme.AppTheme
import com.dd.cloudmusic.widget.Banner
import com.dd.cloudmusic.widget.SearchBar
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun HomePage(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val viewStates = viewModel.viewStates
    val banners = viewStates.banner
    Column(
        modifier = Modifier.background(AppTheme.colors.background)
    ) {
        SearchBar(iconLeftResource = R.mipmap.icon_menu,
            onIconLeftClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }, onSearchClick = {
                showToast("暂未开发")
            }, iconRightResource = R.mipmap.icon_menu,

            onIconRightClick = {
                showToast("暂未开发")
            }
        )
        if (banners.isNotEmpty()) {
            Banner(list = banners) { url, title ->
                showToast("暂未开发$title")
            }
        }
    }
}