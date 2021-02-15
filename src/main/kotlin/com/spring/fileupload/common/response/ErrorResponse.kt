package com.spring.fileupload.common.response

class ErrorResponse {
    var error: String? = null
    var reason: String? = null
    var path: String? = null

    constructor(message: String?) {
        error = message
    }

}