package com.spring.fileupload.common.response

data class ErrorResponse(
    var error: String? = null,
    var reason: String? = null,
    var path: String? = null
)
