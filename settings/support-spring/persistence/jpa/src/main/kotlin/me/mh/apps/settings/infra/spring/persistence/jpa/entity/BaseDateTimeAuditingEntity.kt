package me.mh.apps.settings.infra.spring.persistence.jpa.entity

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.OffsetDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseDateTimeAuditingEntity(
    @CreatedDate
    val createdAt: OffsetDateTime = OffsetDateTime.now(),

    @LastModifiedDate
    val updatedAt: OffsetDateTime = createdAt,
) {

}