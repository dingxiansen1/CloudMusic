package com.dd.base.paging

data class BasicBean<T>(
    var data: T?,
    var code: Int,
    var message: String
)
/*
* 需要分页使用这个
* */
data class ListWrapper<T>(
    var curPage: Int,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int,
    var datas: ArrayList<T>
)
