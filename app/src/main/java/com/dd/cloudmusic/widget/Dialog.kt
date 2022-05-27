package com.dd.base

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.dd.base.utils.SpHelper
import com.dd.cloudmusic.theme.AppTheme
import com.dd.cloudmusic.theme.THEME_COLOR_KEY
import com.dd.cloudmusic.theme.themeColors
import com.dd.cloudmusic.widget.*

@Composable
fun SampleAlertDialog(
    title: String,
    content: String,
    cancelText: String = "取消",
    confirmText: String = "继续",
    onConfirmClick: () -> Unit,
    //onCancelClick: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        title = {
            MediumTitle(title = title)
        },
        text = {
            TextContent(text = content)
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onConfirmClick.invoke()
                onDismiss.invoke()
            }) {
                TextContent(text = confirmText, color = AppTheme.colors.textPrimary)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss.invoke() }) {
                TextContent(text = cancelText)
            }
        },
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    )
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PaletteSelectorDialog(
    initKey: Int,
    title: String = "选择主题",
    confirmText: String = "关闭",
    onDismiss: () -> Unit = {},
    onSelectItem: (index: Int)-> Unit,
) {

    AlertDialog(
        title = {
            MediumTitle(title = title)
        },
        text = {
            LazyVerticalGrid(
                cells = GridCells.Fixed(4),
            ) {
                itemsIndexed(themeColors) { index, color ->
                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .wrapContentSize()
                            .background(AppTheme.colors.mainColor, RoundedCornerShape(24.dp))
                            .clickable {
                                SpHelper.put(THEME_COLOR_KEY, index)
                                onSelectItem.invoke(index)
                                onDismiss.invoke()
                            }
                    ) {
                        Box(
                            modifier = Modifier
                                .width(48.dp)
                                .height(48.dp)
                                .align(Alignment.Center)
                                .clip(RoundedCornerShape(24.dp))
                                .background(color = color)
                        )
                        if (index == initKey) {
                            Icon(
                                imageVector = Icons.Default.Done,
                                contentDescription = "done",
                                tint = AppTheme.colors.mainColor,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDismiss.invoke()
            }) {
                TextContent(text = confirmText, color = AppTheme.colors.textPrimary)
            }
        },
        modifier = Modifier
            //.padding(horizontal = 30.dp)
            .defaultMinSize(minWidth = 300.dp)
            .wrapContentSize()
            .background(AppTheme.colors.mainColor)
    )
}
