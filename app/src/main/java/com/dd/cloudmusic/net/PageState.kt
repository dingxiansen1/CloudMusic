package com.dd.cloudmusic.net
/*
* 网络请求状态
* */
sealed class PageState {
    object Loading : PageState()
    data class Success(val isEmpty: Boolean) : PageState()
    data class Error(val exception: Throwable) : PageState()
}
