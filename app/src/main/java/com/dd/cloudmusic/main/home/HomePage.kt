package com.dd.cloudmusic.main.home

import androidx.compose.foundation.*
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
import com.dd.base.utils.sdp
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
    val state = rememberScrollState()
    val viewStates = viewModel.viewStates
    val banners = viewStates.banner
    val homeIcon = viewStates.homeIcon
    val recommendPlay = viewStates.recommendPlay
    val slidePlay = viewStates.slidePlay
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
        Column(
            //导航栏高度150。如果不设置padding会被导航栏挡住
            Modifier.verticalScroll(state).padding(bottom = 160.sdp)
        ) {
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
            //推荐歌单       showType = HOMEPAGE_SLIDE_PLAYLIST
            if (recommendPlay.isNotNull() && recommendPlay!!.showType == "HOMEPAGE_SLIDE_PLAYLIST") {
                Spacer(
                    modifier = Modifier
                        .height(20.sdp)
                        .fillMaxWidth()
                )
                Box(
                    Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = recommendPlay.uiElement.subTitle.title,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 40.sdp),
                        style = TextStyle(color = AppTheme.colors.textPrimary),
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 40.sdp)
                            .border(
                                width = 1.sdp,
                                color = AppTheme.colors.divider,
                                shape = RoundedCornerShape(50.sdp)
                            )
                            .clip(RoundedCornerShape(50.sdp))
                            .clickable {
                                showToast("暂未开发${recommendPlay.uiElement.button.action}")
                            }
                    ) {
                        Text(
                            "更多",
                            fontSize = 18.sp,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 30.sdp, end = 10.sdp),
                            style = TextStyle(color = AppTheme.colors.textPrimary)
                        )
                        Icon(
                            painter = painterResource(id = R.mipmap.icon_back_right),
                            contentDescription = "更多",
                            modifier = Modifier
                                .size(66.sdp)
                                .align(Alignment.CenterVertically)
                                .padding(end = 20.sdp)
                        )
                    }

                }
                LazyRow {
                    items(recommendPlay.creatives.size) { index ->
                        HomeRecommendPlauyPage(recommendPlay.creatives[index])
                    }
                }
            }
            //推荐歌曲 showType = HOMEPAGE_SLIDE_SONGLIST_ALIGN
            if (slidePlay.isNotNull() && slidePlay!!.showType == "HOMEPAGE_SLIDE_SONGLIST_ALIGN") {
                Spacer(
                    modifier = Modifier
                        .height(20.sdp)
                        .fillMaxWidth()
                )
                Box(
                    Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = slidePlay.uiElement.subTitle.title,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 40.sdp),
                        style = TextStyle(color = AppTheme.colors.textPrimary),
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 40.sdp)
                            .border(
                                width = 1.dp,
                                color = AppTheme.colors.divider,
                                shape = RoundedCornerShape(50.sdp)
                            )
                            .clip(RoundedCornerShape(50.sdp))
                            .clickable {
                                showToast("暂未开发${slidePlay.uiElement.button.action}")
                            }
                    ) {
                        Text(
                            "播放",
                            fontSize = 18.sp,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 30.sdp, end = 10.sdp),
                            style = TextStyle(color = AppTheme.colors.textPrimary)
                        )
                        Icon(
                            painter = painterResource(id = R.mipmap.icon_back_right),
                            contentDescription = "播放",
                            modifier = Modifier
                                .size(66.sdp)
                                .align(Alignment.CenterVertically)
                                .padding(end = 20.sdp)
                        )
                    }
                }
                LazyRow {
                    items(slidePlay.creatives.size) { index ->
                        HomeRecommendSongPage(slidePlay.creatives[index])
                    }
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

/*
* 推荐歌单
* */
@ExperimentalPagerApi
@Composable
fun HomeRecommendPlauyPage(data: Creative) {
    if (data.creativeType == "scroll_playlist") {
        ScrollPlayList(list = data.resources) {
            showToast("暂未开发：${it.uiElement.mainTitle.title}")
        }
    } else {
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

/*
* 推荐歌曲
* */
@Composable
fun HomeRecommendSongPage(creative: Creative) {
    Column(Modifier.width(860.sdp)) {
        for (item in creative.resources) {
            Row(Modifier.padding(10.sdp)) {
                AsyncImage(
                    model = item.uiElement.image.imageUrl,
                    contentDescription = item.uiElement.mainTitle.title,
                    modifier = Modifier
                        .height(80.sdp)
                        .width(80.sdp)
                        .clip(shape = RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = item.uiElement.mainTitle.title,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.sdp)
                        .align(Alignment.CenterVertically),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(color = AppTheme.colors.textPrimary)
                )
            }
        }
    }
}


