package com.spring.fileupload.filecontent.database

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FileRecordRepository : JpaRepository<FileRecord, Long> {
    fun findBySha256(sha256: String): Optional<FileRecord>
    fun findByDataId(dataId: String): Optional<FileRecord>
}