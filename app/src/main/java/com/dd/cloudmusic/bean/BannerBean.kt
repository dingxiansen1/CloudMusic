package com.dd.cloudmusic.bean

data class basebanner<T>(
    val banners: List<Banner>?,
    val code: Int
)

data class Banner(
    val adDispatchJson: Any?,
    val adLocation: Any?,
    val adSource: Any?,
    val adid: Any?,
    val encodeId: String?,
    val event: Any?,
    val exclusive: Boolean?,
    val extMonitor: Any?,
    val extMonitorInfo: Any?,
    val imageUrl: String,
    val monitorBlackList: Any?,
    val monitorClick: Any?,
    val monitorClickList: Any?,
    val monitorImpress: Any?,
    val monitorImpressList: Any?,
    val monitorType: Any?,
    val program: Any?,
    val scm: String?,
    val song: Any?,
    val targetId: Long,
    val targetType: Int,
    val titleColor: String?,
    val typeTitle: String,
    val url: Any?,
    val video: Any?
)