package com.dd.cloudmusic.main.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dd.base.BaseViewModel
import com.dd.base.ext.launch
import com.dd.base.utils.log.LogUtils
import com.dd.cloudmusic.bean.Banner
import com.dd.cloudmusic.bean.Block
import com.dd.cloudmusic.bean.HomeIconBean
import com.dd.cloudmusic.net.HttpService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private var service: HttpService,
) : BaseViewModel() {

    var viewStates by mutableStateOf(HomeViewState())
        private set

    init {
        getData()
    }

    private fun getData() {
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
        launch{
            combine(bannerFlow, homeIconFlow, homePageFlow) { banners, icons, bean ->
                var  recommendPlay:Block?=null
                var  slidePlay:Block?=null
                for(item in bean){
                    when(item.showType){
                        HOMEPAGE_SLIDE_PLAYLIST ->{
                            recommendPlay = item
                        }
                        HOMEPAGE_SLIDE_SONGLIST_ALIGN ->{
                            slidePlay = item
                        }
                    }
                }
                viewStates =
                    viewStates.copy(
                        isRefreshing = false,
                        banner = banners,
                        homeIcon = icons,
                        recommendPlay =recommendPlay,
                        slidePlay =slidePlay
                    )
            }.onStart {
                viewStates = viewStates.copy(isRefreshing = true)
            }.catch {
                viewStates = viewStates.copy(isRefreshing = false)
            }.collect()
        }
    }

}

const val HOMEPAGE_SLIDE_PLAYLIST = "HOMEPAGE_SLIDE_PLAYLIST"
const val HOMEPAGE_SLIDE_SONGLIST_ALIGN = "HOMEPAGE_SLIDE_SONGLIST_ALIGN"

data class HomeViewState(
    val isRefreshing: Boolean = false,
    val banner: List<Banner> = emptyList(),
    val homeIcon: List<HomeIconBean> = emptyList(),
    val recommendPlay: Block? = null,
    val slidePlay: Block? = null
)