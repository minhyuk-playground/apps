package me.mh.apps.settings.infra.spring.persistence.jpa.system.properties

import org.hibernate.TimeZoneStorageStrategy
import org.hibernate.boot.SchemaAutoTooling
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy
import org.hibernate.cfg.AvailableSettings
import org.hibernate.dialect.MySQLDialect
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy
import org.springframework.orm.jpa.vendor.Database

data class JpaCustomProperties(
    val database: Database,
    val databaseDialect: String,
    val ddlAuto: SchemaAutoTooling,
    val timezoneDefaultStorage: TimeZoneStorageStrategy,
    val jdbcBatchSize: Int,
    val defaultBatchFetchSize: Int,
    val isShowSql: Boolean
) {
    fun toJpaProperties(): JpaProperties {
        return JpaProperties().apply {
            database = this@JpaCustomProperties.database
            databasePlatform = databaseDialect
            isShowSql = this@JpaCustomProperties.isShowSql
            isGenerateDdl = false
            openInView = false

            val isShowSqlString = isShowSql.toString()
            properties = mapOf(
                AvailableSettings.HBM2DDL_AUTO to ddlAuto.name,
                AvailableSettings.TIMEZONE_DEFAULT_STORAGE to timezoneDefaultStorage.name,
                AvailableSettings.STATEMENT_BATCH_SIZE to jdbcBatchSize.toString(),
                AvailableSettings.DEFAULT_BATCH_FETCH_SIZE to defaultBatchFetchSize.toString(),
                AvailableSettings.IMPLICIT_NAMING_STRATEGY to SpringImplicitNamingStrategy::class.qualifiedName,
                AvailableSettings.PHYSICAL_NAMING_STRATEGY to CamelCaseToUnderscoresNamingStrategy::class.qualifiedName,
                AvailableSettings.SHOW_SQL to isShowSqlString,
                AvailableSettings.FORMAT_SQL to isShowSqlString,
                AvailableSettings.HIGHLIGHT_SQL to isShowSqlString,
            )
        }
    }

    companion object {
        fun mysql(): JpaCustomProperties = JpaCustomProperties(
            database = Database.MYSQL,
            databaseDialect = MySQLDialect::class.qualifiedName!!,
            ddlAuto = SchemaAutoTooling.VALIDATE,
            timezoneDefaultStorage = TimeZoneStorageStrategy.NORMALIZE,
            jdbcBatchSize = 100,
            defaultBatchFetchSize = 100,
            isShowSql = true
        )
    }
}
