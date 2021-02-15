package com.spring.fileupload.filecontent.model

data class FileRecordDto(
    var dataId: String? = null,
    var name: String? = null,
    var fileStatus: FileStatus?
)