package com.dd.cloudmusic.main.home

import Block
import com.dd.cloudmusic.bean.Banner
import com.dd.cloudmusic.bean.HomeIconBean
import kotlinx.serialization.Serializable


const val NET_DATA ="net_data"

const val HOMEPAGE_SLIDE_PLAYLIST = "HOMEPAGE_SLIDE_PLAYLIST"
const val HOMEPAGE_SLIDE_SONGLIST_ALIGN = "HOMEPAGE_SLIDE_SONGLIST_ALIGN"

data class HomeViewState(
    val isRefreshing: Boolean = false,
    val banner: List<Banner> = emptyList(),
    val homeIcon: List<HomeIconBean> = emptyList(),
    val recommendPlay: Block? = null,
    val slidePlay: Block? = null
)

