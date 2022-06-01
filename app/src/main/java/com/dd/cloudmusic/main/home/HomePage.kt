package com.dd.cloudmusic.main.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
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
            Box(Modifier.fillMaxWidth().height(50.dp)) {
                Text(
                    text = "推荐歌单",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 20.dp)
                )

                IconButton(
                    onClick = { showToast("暂未开发") }, Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 20.dp)
                ) {
                    Text(text = "更多", fontSize = 18.sp)
                    Icon(painter = painterResource(id = R.mipmap.icon_back_right), contentDescription = "更多")
                }
            }
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
    Column(
        modifier = Modifier
            .height(150.dp)
            .width(150.dp)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = data.uiElement.image.imageUrl,
            contentDescription = data.uiElement.mainTitle.title,
            modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(10.dp)),
        )
        Text(
            text = data.uiElement.mainTitle.title,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(color = AppTheme.colors.textPrimary)
        )
    }
}


