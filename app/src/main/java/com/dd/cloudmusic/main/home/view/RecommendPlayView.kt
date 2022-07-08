package com.dd.cloudmusic.main.home.view

import Block
import Creative
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dd.base.ext.showToast
import com.dd.base.utils.sdp
import com.dd.cloudmusic.R
import com.dd.cloudmusic.theme.AppTheme
import com.dd.cloudmusic.widget.ScrollPlayList
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RecommendPlayView(recommendPlay: Block) {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.sdp)
            .background(AppTheme.colors.divider)
    )
    Spacer(
        modifier = Modifier
            .height(30.sdp)
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
    Spacer(
        modifier = Modifier
            .height(20.sdp)
            .fillMaxWidth()
    )
    LazyRow {
        items(recommendPlay.creatives.size) { index ->
            HomeRecommendPlauyPage(recommendPlay.creatives[index])
        }
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
                .padding(20.sdp)
                .width(300.sdp)
                .clickable {
                    showToast("暂未开发")
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = data.uiElement.image.imageUrl,
                contentDescription = data.uiElement.mainTitle.title,
                modifier = Modifier
                    .size(300.sdp)
                    .clip(shape = RoundedCornerShape(50.sdp)),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = data.uiElement.mainTitle.title,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.sdp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(color = AppTheme.colors.textPrimary)
            )
        }
    }
}

