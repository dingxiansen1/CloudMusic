package com.dd.cloudmusic.splash

import Block
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dd.base.BaseViewModel
import com.dd.base.ext.launch
import com.dd.base.utils.DataStoreUtils
import com.dd.base.utils.log.LogUtils
import com.dd.cloudmusic.main.home.HOMEPAGE_SLIDE_PLAYLIST
import com.dd.cloudmusic.main.home.HOMEPAGE_SLIDE_SONGLIST_ALIGN
import com.dd.cloudmusic.main.home.HomeViewState
import com.dd.cloudmusic.net.HttpService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private var service: HttpService,
) : BaseViewModel() {

    fun getData(){
        launch(Dispatchers.IO) {
            val banner = async {
                service.getBanner().banners ?: emptyList()
            }
            val homeIcon = async {
                service.getHomeIcon().data ?: emptyList()
            }
            val homeData = async {
                service.getHomePage().data?.blocks ?: emptyList()
            }
            var recommendPlay: Block? = null
            var slidePlay: Block? = null
            for (item in homeData.await()) {
                when (item.showType) {
                    HOMEPAGE_SLIDE_PLAYLIST -> {
                        recommendPlay = item
                    }
                    HOMEPAGE_SLIDE_SONGLIST_ALIGN -> {
                        slidePlay = item
                    }
                }
            }
            SplashModel.homeData.tryEmit(HomeViewState(false,banner.await(),homeIcon.await(),recommendPlay,slidePlay))
        }
    }


}