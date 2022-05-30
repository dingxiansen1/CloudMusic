package com.dd.cloudmusic.main.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.dd.base.BaseViewModel
import com.dd.cloudmusic.bean.Banner
import com.dd.cloudmusic.bean.HomeIconBean
import com.dd.cloudmusic.net.HttpService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private var service: HttpService,
) : BaseViewModel() {

    init {
        getData()
    }

    var viewStates by mutableStateOf(HomeViewState())
        private set

    /**
     *  轮播
     * */
    private val bannerFlow = flow {
        emit(service.getBanner())
    }.map {
        it.banners ?: emptyList()
    }
    /**
     *  首页Icon
     * */
    private val homeIconFlow = flow {
        emit(service.getHomeIcon())
    }.map {
        it.data ?: emptyList()
    }
    private fun getData() {
        viewModelScope.launch {
            bannerFlow.zip(homeIconFlow){banners, icons ->
                viewStates =
                    viewStates.copy(isRefreshing = false,banner = banners,homeIcon = icons)
            }.onStart {
                viewStates = viewStates.copy(isRefreshing = true)
            }.catch {
                viewStates = viewStates.copy(isRefreshing = false)
            }.collect()
        }
    }

}

data class HomeViewState(
    val isRefreshing: Boolean = false,
    val banner: List<Banner> = emptyList(),
    val homeIcon: List<HomeIconBean> = emptyList()
)