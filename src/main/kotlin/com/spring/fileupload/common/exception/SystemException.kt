package com.spring.fileupload.common.exception

class SystemException : RuntimeException {
    constructor(errorMessage: String?) : super(errorMessage) {}
    constructor(throwable: Throwable?) : super(throwable) {}
    constructor(errorMessage: String?, throwable: Throwable?) : super(errorMessage, throwable) {}
}