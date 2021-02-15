package com.spring.fileupload.common.model

interface Converter<T, S> {
    fun convert(t: T): S
}