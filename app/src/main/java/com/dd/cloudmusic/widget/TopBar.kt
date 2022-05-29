package com.dd.cloudmusic.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd.cloudmusic.R
import com.dd.cloudmusic.theme.AppTheme

@Composable
fun SearchBar(
    iconLeftResource:Int?=null,
    onIconLeftClick:(() -> Unit)?=null,
    onSearchClick: () -> Unit,
    searchText:String?=null,
    iconRightResource:Int?=null,
    onIconRightClick:(() -> Unit)?=null,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        if(onIconLeftClick!=null) {
            Icon(
                //默认显示返回图标
                painter = painterResource(id = iconLeftResource ?: R.mipmap.icon_back),
                contentDescription = "",
                modifier = Modifier
                    .size(48.dp)
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
                    .clickable {
                        onIconLeftClick.invoke()
                    }
            )
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .height(36.dp)
                .align(alignment = Alignment.CenterVertically)
                .weight(1f)
                .background(
                    shape = RoundedCornerShape(12.5.dp),
                    color = AppTheme.colors.divider
                )
                .clickable { onSearchClick() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_search),
                contentDescription = "搜索",
                modifier = Modifier
                    .size(30.dp)
                    .padding(start = 10.dp)
                    .align(Alignment.CenterVertically)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(start = 10.dp)
            ) {
                Text(text = searchText?: "搜索", fontSize = 14.sp,color = AppTheme.colors.textSecondary)
            }
        }
        if(onIconRightClick!=null&&iconRightResource!==null) {
            Icon(
                painter = painterResource(id = iconRightResource),
                contentDescription = "",
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 16.dp)
                    .align(Alignment.CenterVertically)
                    .clickable {
                        onIconRightClick.invoke()
                    }
            )
        }
    }
}

@Composable
fun TitleBar(
    iconResource:Int?,
    onIconClick:(() -> Unit)?=null,
    title :String?
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        if(onIconClick!=null){
            Icon(
                //默认显示返回图标
                painter = painterResource( id = iconResource?: R.mipmap.icon_back),
                contentDescription = "",
                modifier = Modifier
                    .size(48.dp)
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
                    .clickable {
                        onIconClick.invoke()
                    }
            )
        }
        Text(text = "$title",textAlign = TextAlign.Center)
    }
}