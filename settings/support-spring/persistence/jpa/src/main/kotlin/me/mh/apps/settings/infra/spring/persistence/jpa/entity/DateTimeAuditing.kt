package me.mh.apps.settings.infra.spring.persistence.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.OffsetDateTime

@Embeddable
data class DateTimeAuditing(
    @Column(insertable = false, updatable = false)
    val createdAt: OffsetDateTime = OffsetDateTime.now(),

    @Column(insertable = false, updatable = false)
    val updatedAt: OffsetDateTime = createdAt,
)
