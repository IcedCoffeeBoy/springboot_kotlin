package com.spring.fileupload.common.response

data class PageDto<T>(
    var content: List<T?>,
    var pageNumber: Int?,
    var pageSize: Int?,
    var totalPages: Int?,
    var totalElements: Int?,
)

