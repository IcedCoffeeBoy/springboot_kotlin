package com.spring.fileupload.filecontent.database

import com.spring.fileupload.config.audit.AuditBaseEntity
import com.spring.fileupload.filecontent.model.FileRecordDto
import com.spring.fileupload.filecontent.model.FileStatus
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.validation.constraints.Size

/**
 * File record for saving all information for all files uploaded
 */
@Entity
@SequenceGenerator(name = "fileRecordSeqId", sequenceName = "file_record_seq_id", allocationSize = 1)
data class FileRecord(
    @get:Id
    @get:GeneratedValue(generator = "fileContentId", strategy = GenerationType.AUTO)
    @get:Column
    var id: Long?,

    @get:Column(name = "NAME", nullable = false)
    var name: String?,

    @get:Column(name = "SHA_256", nullable = false, unique = true)
    var sha256: @Size(max = 64) String,

    @get:Column
    @get:Enumerated(EnumType.STRING)
    var fileStatus: FileStatus,

    @get:Column
    var dataId: String
) : AuditBaseEntity()

fun FileRecord.toDto(): FileRecordDto {
    return FileRecordDto(dataId = this.dataId, name = this.name, fileStatus = this.fileStatus)
}

