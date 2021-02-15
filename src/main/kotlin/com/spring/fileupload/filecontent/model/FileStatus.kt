package com.spring.fileupload.filecontent.model

enum class FileStatus(val label: String) {
    PROCESSING("processing"), ERROR("error"), COMPLETED("completed");
}