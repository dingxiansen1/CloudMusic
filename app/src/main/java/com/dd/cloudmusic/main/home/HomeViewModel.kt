package com.dd.cloudmusic.main.home

import Block
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dd.base.BaseViewModel
import com.dd.base.ext.launch
import com.dd.base.utils.DataStoreUtils
import com.dd.base.utils.log.LogUtils
import com.dd.cloudmusic.net.HttpService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private var service: HttpService,
) : BaseViewModel() {

    var viewStates by mutableStateOf(HomeViewState())
        private set

    init {
        viewStates = getLocalData()
        getNetData()
    }


    private fun getLocalData(): HomeViewState {
        DataStoreUtils.readStringData(NET_DATA).let {
            if (it != "") {
                return Json.decodeFromString(it)
            }
            return HomeViewState(isRefreshing = true)
        }
    }

    private fun getNetData() {
        // 轮播
        val bannerFlow = flow {
            emit(service.getBanner())
        }.map {
            it.banners ?: emptyList()
        }.catch {
            LogUtils.e("请求homebannerFlow失败：${it}")
        }

        // 首页Icon
        val homeIconFlow = flow {
            emit(service.getHomeIcon())
        }.map {
            it.data ?: emptyList()
        }.catch {
            LogUtils.e("请求homeIconFlow失败：${it}")
        }

        // 首页主要信息
        val homePageFlow = flow {
            emit(service.getHomePage())
        }.map {
            it.data?.blocks ?: emptyList()
        }.catch {
            LogUtils.e("请求homePageFlow失败：${it}")
        }

        launch {
            combine(bannerFlow, homeIconFlow, homePageFlow) { banners, icons, bean ->
                var recommendPlay: Block? = null
                var slidePlay: Block? = null
                for (item in bean) {
                    when (item.showType) {
                        HOMEPAGE_SLIDE_PLAYLIST -> {
                            recommendPlay = item
                        }
                        HOMEPAGE_SLIDE_SONGLIST_ALIGN -> {
                            slidePlay = item
                        }
                    }
                }
                val data =  HomeViewState(
                    isRefreshing = false,
                    banner = banners,
                    homeIcon = icons,
                    recommendPlay = recommendPlay,
                    slidePlay = slidePlay
                )
                if (viewStates.isRefreshing){
                    viewStates = data
                }
                saveNetData(data)
            }.catch {
                viewStates = viewStates.copy(isRefreshing = false)
            }.collect()
        }
    }

    suspend fun saveNetData(viewStates :HomeViewState){
        val jsonString = Json.encodeToString(viewStates)
        DataStoreUtils.putData(jsonString, NET_DATA)
    }

}