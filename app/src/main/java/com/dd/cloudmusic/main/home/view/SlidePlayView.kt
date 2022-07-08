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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dd.base.ext.showToast
import com.dd.base.utils.sdp
import com.dd.cloudmusic.R
import com.dd.cloudmusic.bean.HomeIconBean
import com.dd.cloudmusic.theme.AppTheme


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


@Composable
fun SlidePlayView(slidePlay: Block) {
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
    Spacer(
        modifier = Modifier
            .height(20.sdp)
            .fillMaxWidth()
    )
    LazyRow {
        items(slidePlay.creatives.size) { index ->
            HomeRecommendSongPage(slidePlay.creatives[index])
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
            Row(Modifier.padding(20.sdp)) {
                AsyncImage(
                    model = item.uiElement.image.imageUrl,
                    contentDescription = item.uiElement.mainTitle.title,
                    modifier = Modifier
                        .size(150.sdp)
                        .clip(shape = RoundedCornerShape(30.sdp)),
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = item.uiElement.mainTitle.title,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(start = 20.sdp)
                        .align(Alignment.CenterVertically),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(color = AppTheme.colors.textPrimary)
                )
                Text(
                    text = "-${item.resourceExtInfo.artists[0].name}",
                    fontSize = 14.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.sdp)
                        .align(Alignment.CenterVertically),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(color = AppTheme.colors.textSecondary)
                )
            }
        }
    }
}