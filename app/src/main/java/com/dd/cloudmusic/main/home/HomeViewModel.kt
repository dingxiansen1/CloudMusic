package com.dd.cloudmusic.main.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.dd.base.BaseViewModel
import com.dd.cloudmusic.bean.Banner
import com.dd.cloudmusic.net.HttpService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private var service: HttpService,
) : BaseViewModel() {

    var viewStates by mutableStateOf(HomeViewState())
        private set

    init {
        getBanner()
    }

    private fun getBanner() {
        val bannerFlow = flow {
            emit(service.getBanner())
        }.map {
            val result = mutableListOf<Banner>()
            it.let {
                if (it.banners != null) {
                    result.addAll(it.banners)
                }
            }
            result
        }
        viewModelScope.launch {
            bannerFlow.onStart {
                viewStates = viewStates.copy(isRefreshing = true)
            }.catch {
                viewStates = viewStates.copy(isRefreshing = false)
            }.collect {
                viewStates =
                    viewStates.copy(banner = it, isRefreshing = false)
            }
        }

    }

}

data class HomeViewState(
    val isRefreshing: Boolean = false,
    val banner: List<Banner> = emptyList()
)