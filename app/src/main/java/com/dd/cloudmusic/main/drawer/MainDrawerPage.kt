package com.dd.cloudmusic.main.drawer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dd.cloudmusic.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainDrawerPage(
    scaffoldState: ScaffoldState,
    scope: CoroutineScope
) {
    Box {
       /* Image(
            painter = painterResource(id = R.mipmap.icon_default_user),
            contentDescription = null
        )*/
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Image(
                painter = painterResource(id = R.mipmap.icon_default_user),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(65.dp)
                    .border(BorderStroke(1.dp, Color.Gray), CircleShape)
            )
            Spacer(Modifier.padding(vertical = 8.dp))
            Text(
                text = "游客12345",
                style = MaterialTheme.typography.body2
            )
        }
    }

    ListItem(
        icon = {
            Icon(Icons.Filled.Home, null)
        },
        modifier = Modifier
            .clickable {

            }
    ) {
        Text("首页")
    }

    Box(
        modifier = Modifier.fillMaxHeight(),
        contentAlignment= Alignment.BottomStart
    ) {
        TextButton(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.onSurface),
        ) {
            Icon(Icons.Filled.Settings, null)
            Text("设置")
        }
    }

    // 编写逻辑
    // 如果 drawer 已经展开了，那么点击返回键收起而不是直接退出 app

    BackHandler(enabled = scaffoldState.drawerState.isOpen) {
        scope.launch {
            scaffoldState.drawerState.close()
        }
    }
}