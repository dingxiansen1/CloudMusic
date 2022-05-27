package com.dd.cloudmusic.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dd.cloudmusic.R

@Composable
fun SearchBar(
    onSearchClick: () -> Unit,
) {

}

@Composable
fun TitleBar(
    iconResource:Int?,
    onIconClick: () -> Unit,
    title :String?
) {
    Row() {
        Icon(
            //默认显示返回图标
            painter = painterResource( id = iconResource?: R.mipmap.icon_back),
            contentDescription = "用户",
            modifier = Modifier
                .size(25.dp)
                .padding(start = 10.dp)
                .align(Alignment.CenterVertically)
                .clickable {
                    onIconClick.invoke()
                }
        )
        Text(text = "$title",textAlign = TextAlign.Center)
    }
}