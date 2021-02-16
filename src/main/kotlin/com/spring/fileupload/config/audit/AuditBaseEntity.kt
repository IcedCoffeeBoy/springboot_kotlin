package com.spring.fileupload.config.audit

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import javax.persistence.Version

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class AuditBaseEntity(
    @get:Version
    var version: Long? = null,

    @get:Column
    @CreatedBy
    var createdBy: String? = null,

    @get:Column
    @get:CreatedDate
    var createdDate: LocalDateTime? = null,

    @get:Column
    @get:LastModifiedBy
    var lastModifiedBy: String? = null,

    @get:Column
    @get:LastModifiedDate
    var lastModifiedDate: LocalDateTime? = null
)
