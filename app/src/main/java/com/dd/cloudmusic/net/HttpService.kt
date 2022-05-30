package com.dd.cloudmusic.net

import com.dd.base.paging.BasicBean
import com.dd.cloudmusic.bean.Banner
import com.dd.cloudmusic.bean.HomeIconBean
import com.dd.cloudmusic.bean.basebanner
import retrofit2.http.GET

/**
 * 网络请求接口
 * 注意：接口前无需加斜杠
 */
interface HttpService {

    companion object {
        const val url = "https://netease-cloud-music-api-nine-blush.vercel.app/"
    }

    @GET("/banner")
    suspend fun getBanner(): basebanner<List<Banner>?>

    @GET("/homepage/dragon/ball")
    suspend fun getHomeIcon(): BasicBean<List<HomeIconBean>?>
}
