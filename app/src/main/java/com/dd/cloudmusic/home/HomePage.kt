package com.dd.cloudmusic.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dd.cloudmusic.R
import com.dd.cloudmusic.theme.AppTheme

@Composable
fun HomePage(navCtrl: NavHostController) {
    Scaffold(
        modifier = Modifier.background(AppTheme.colors.background),
        topBar = {
            Row() {
                Icon(
                    painter = painterResource(id = R.mipmap.icon_default_user),
                    contentDescription = "用户",
                    modifier = Modifier
                        .size(25.dp)
                        .padding(start = 10.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    ) {

    }
}