package com.dd.cloudmusic.main.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dd.base.ext.showToast
import com.dd.base.utils.sdp
import com.dd.cloudmusic.R
import com.dd.cloudmusic.bean.Block
import com.dd.cloudmusic.main.home.view.*
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
            Modifier
                .verticalScroll(state)
                .padding(bottom = 160.sdp)
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
                Spacer(
                    modifier = Modifier
                        .height(30.sdp)
                        .fillMaxWidth()
                )
            }
            //推荐歌单 showType = HOMEPAGE_SLIDE_PLAYLIST
            if (recommendPlay != null) {
                RecommendPlayView(recommendPlay)
            }
            //推荐歌曲 showType = HOMEPAGE_SLIDE_SONGLIST_ALIGN
            if (slidePlay != null) {
                SlidePlayView(slidePlay)
            }

        }

    }
}



