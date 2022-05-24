package com.dd.cloudmusic.theme

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.dd.base.utils.SpHelper

object Themem {

    var themeIndex = mutableStateOf(getTheme())
    var selectTheme = mutableStateOf<Color?>(null)

    fun getTheme():Int{
        return SpHelper.getInt(THEME_COLOR_KEY)!!;
    }
    /**
     * 标题栏和导航栏颜色
     */
    val themeTypeState: MutableState<ThemeType> = mutableStateOf(getThemeType())


    /**
     * 获取保存下来的主题颜色
     */
    private fun getThemeType(): ThemeType {
        return when(SpHelper.getInt(THEME_COLOR_KEY) ?: 0) {
            0 -> ThemeType.Default
            1 -> ThemeType.Theme1
            2 -> ThemeType.Theme2
            3 -> ThemeType.Theme3
            4 -> ThemeType.Theme4
            5-> ThemeType.Theme5
            else -> ThemeType.Default
        }
    }
    public fun setTheme(int: Int) {
        themeTypeState.value = when (int) {
            0 -> ThemeType.Default
            1 -> ThemeType.Theme1
            2 -> ThemeType.Theme2
            3 -> ThemeType.Theme3
            4 -> ThemeType.Theme4
            5 -> ThemeType.Theme5
            else -> ThemeType.Default
        }
    }
}