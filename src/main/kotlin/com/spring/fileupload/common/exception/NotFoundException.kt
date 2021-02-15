package com.spring.fileupload.common.exception

class NotFoundException : RuntimeException {
    constructor(resource: Class<*>) : super(String.format(ERROR_MESSAGE_TEMPLATE, resource.simpleName)) {}
    constructor(errorMessage: String?) : super(errorMessage) {}
    constructor(throwable: Throwable?) : super(throwable) {}
    constructor(errorMessage: String?, throwable: Throwable?) : super(errorMessage, throwable) {}

    companion object {
        private const val ERROR_MESSAGE_TEMPLATE = "The entity/resource %s is not found"
    }
}