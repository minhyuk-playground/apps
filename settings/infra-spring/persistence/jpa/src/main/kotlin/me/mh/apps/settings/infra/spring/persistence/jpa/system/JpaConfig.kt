package me.mh.apps.settings.infra.spring.persistence.jpa.system

import me.mh.apps.settings.infra.spring.persistence.jpa.system.properties.JpaCustomProperties
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.auditing.DateTimeProvider
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.time.OffsetDateTime
import java.util.*

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider", auditorAwareRef = "auditorAware")
@EnableConfigurationProperties(JpaCustomProperties::class)
internal class JpaConfig {
    @Bean
    @ConditionalOnMissingBean(DateTimeProvider::class)
    fun dateTimeProvider(): DateTimeProvider = DateTimeProvider {
        Optional.of(OffsetDateTime.now())
    }

    @Bean
    @ConditionalOnMissingBean(AuditorAware::class)
    fun auditorAware(): AuditorAware<Any> = AuditorAware<Any> {
        Optional.of("system")
    }
}