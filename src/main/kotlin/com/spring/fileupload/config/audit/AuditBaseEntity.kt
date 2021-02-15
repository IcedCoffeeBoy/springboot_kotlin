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
open class AuditBaseEntity {
    @Version
    private val version: Long? = null

    @Column
    @CreatedBy
    private val createdBy: String? = null

    @Column
    @CreatedDate
    private val createdDate: LocalDateTime? = null

    @Column
    @LastModifiedBy
    private val lastModifiedBy: String? = null

    @Column
    @LastModifiedDate
    private val lastModifiedDate: LocalDateTime? = null
}