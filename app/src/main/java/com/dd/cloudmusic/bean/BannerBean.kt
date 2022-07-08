package com.dd.cloudmusic.bean

import kotlinx.serialization.Serializable

data class basebanner<T>(
    val banners: List<Banner>?,
    val code: Int
)
@Serializable
data class Banner(
    val adDispatchJson: String?,
    val adLocation: String?,
    val adSource: String?,
    val adid: String?,
    val encodeId: String?,
    val event: String?,
    val exclusive: Boolean?,
    val extMonitor: String?,
    val extMonitorInfo: String?,
    val imageUrl: String,
    val monitorBlackList: String?,
    val monitorClick: String?,
    val monitorClickList: String?,
    val monitorImpress: String?,
    val monitorImpressList: String?,
    val monitorType: String?,
    val program: String?,
    val scm: String?,
    val song: String?,
    val targetId: Long,
    val targetType: Int,
    val titleColor: String?,
    val typeTitle: String,
    val url: String?,
    val video: String?
)