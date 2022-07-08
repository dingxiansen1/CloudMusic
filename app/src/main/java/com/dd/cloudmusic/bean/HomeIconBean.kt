package com.dd.cloudmusic.bean

import kotlinx.serialization.Serializable

@Serializable
data class HomeIconBean(
    val homepageMode: String,
    val iconUrl: String,
    val id: Int,
    val name: String,
    val skinSupport: Boolean,
    val url: String
)