package com.dd.cloudmusic.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bbgo.common_base.ext.isNotNull
import com.dd.base.ext.showToast
import com.dd.cloudmusic.R
import com.dd.cloudmusic.bean.Creative
import com.dd.cloudmusic.bean.HomeIconBean
import com.dd.cloudmusic.theme.AppTheme
import com.dd.cloudmusic.widget.Banner
import com.dd.cloudmusic.widget.ScrollPlayList
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
    val recommendPlay= viewStates.recommendPlay
    Column(
        modifier = Modifier.background(AppTheme.colors.background)
    ) {
        SearchBar(
            iconLeftResource = R.mipmap.icon_menu,
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
        //推荐歌单
        if (recommendPlay.isNotNull()) {
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth()
            )
            Box(
                Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = recommendPlay!!.uiElement.subTitle.title,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 20.dp),
                    style = TextStyle(color = AppTheme.colors.textPrimary),
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 20.dp)
                        .border(
                            width = 1.dp,
                            color = AppTheme.colors.divider,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clickable {
                            showToast("暂未开发${recommendPlay.uiElement.button.action}")
                        }
                ) {
                    Text(
                        "更多",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 20.dp,end = 10.dp),
                        style = TextStyle(color = AppTheme.colors.textPrimary)
                    )
                    Icon(
                        painter = painterResource(id = R.mipmap.icon_back_right),
                        contentDescription = "更多",
                        modifier = Modifier
                            .size(36.dp)
                            .align(Alignment.CenterVertically)
                            .padding(end = 20.dp)
                    )
                }

            }
            LazyRow {
                items(recommendPlay!!.creatives.size) { index ->
                    HomeRecommendPage(recommendPlay.creatives[index])
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
            .width(80.dp)
            .clickable {
                showToast("暂未开发")
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = data.iconUrl,
            contentDescription = data.name,
            modifier = Modifier.size(56.dp),
            colorFilter = ColorFilter.tint(AppTheme.colors.error)
        )
        Text(
            text = data.name,
            fontSize = 16.sp,
            style = TextStyle(color = AppTheme.colors.textPrimary)
        )
    }
}

@ExperimentalPagerApi
@Composable
fun HomeRecommendPage(data: Creative) {
    if (data.creativeType == "scroll_playlist") {
        ScrollPlayList(list = data.resources){
            showToast("暂未开发：${it.uiElement.mainTitle.title}")
        }
    }else{
        Column(
            modifier = Modifier
                .width(150.dp)
                .padding(10.dp)
                .clickable {
                    showToast("暂未开发")
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = data.uiElement.image.imageUrl,
                contentDescription = data.uiElement.mainTitle.title,
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .clip(shape = RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = data.uiElement.mainTitle.title,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(color = AppTheme.colors.textPrimary)
            )
        }
    }
}


