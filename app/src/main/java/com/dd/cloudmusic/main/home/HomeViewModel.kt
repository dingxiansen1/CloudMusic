package com.dd.cloudmusic.main.home

import Block
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dd.base.BaseViewModel
import com.dd.base.ext.launch
import com.dd.base.ext.observerFlow
import com.dd.base.utils.DataStoreUtils
import com.dd.base.utils.log.LogUtils
import com.dd.cloudmusic.net.HttpService
import com.dd.cloudmusic.splash.SplashModel
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

    var viewStates by mutableStateOf(HomeViewState(isRefreshing = false))
        private set

    init {
        observerFlow(SplashModel.homeData){
            viewStates =  it
        }
    }

}