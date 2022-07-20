package com.dd.cloudmusic.splash

import Block
import com.dd.base.utils.log.LogUtils
import com.dd.cloudmusic.main.home.HOMEPAGE_SLIDE_PLAYLIST
import com.dd.cloudmusic.main.home.HOMEPAGE_SLIDE_SONGLIST_ALIGN
import com.dd.cloudmusic.main.home.HomeViewState
import com.dd.cloudmusic.net.HttpService
import kotlinx.coroutines.flow.*

object SplashModel {
    val homeData = MutableSharedFlow<HomeViewState>()
}