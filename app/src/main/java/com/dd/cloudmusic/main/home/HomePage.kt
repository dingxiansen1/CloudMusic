package com.dd.cloudmusic.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.dd.base.ext.showToast
import com.dd.cloudmusic.R
import com.dd.cloudmusic.bean.Creative
import com.dd.cloudmusic.bean.HomeIconBean
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
    val homeIcon = viewStates.homeIcon
    val recommendPlayList = viewStates.recommendPlayList
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
        if (homeIcon.isNotEmpty()) {
            LazyRow {
                items(homeIcon.size) { index ->
                    HomeIconPage(homeIcon[index])
                }
            }
        }
        if (recommendPlayList.isNotEmpty()) {
            LazyRow {
                items(recommendPlayList.size) { index ->
                    HomeRecommendPage(recommendPlayList[index])
                }
            }
        }
    }
}

@Composable
fun HomeIconPage(data: HomeIconBean) {
    Column(
        modifier = Modifier
            .height(80.dp)
            .width(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = data.iconUrl,
            contentDescription = data.name,
            modifier = Modifier.size(56.dp),
            colorFilter = ColorFilter.tint(AppTheme.colors.error)
        )
        Text(text = data.name, fontSize = 16.sp)
    }
}

@Composable
fun HomeRecommendPage(data: Creative) {
    Box(
        modifier = Modifier
            .height(150.dp)
            .width(150.dp)
            .padding(10.dp)
    ) {
        AsyncImage(
            model = data.uiElement.image.imageUrl,
            contentDescription = data.uiElement.mainTitle.title,
            modifier = Modifier.fillMaxSize()
        )
        Text(
            text = data.uiElement.mainTitle.title,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .align(
                    Alignment.BottomCenter
                ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(color = AppTheme.colors.white)
        )
    }
}


