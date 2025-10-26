package me.mh.apps.settings.infra.spring.persistence.jpa.system

import me.mh.apps.settings.infra.spring.persistence.jpa.system.properties.JpaCustomProperties
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.sql.DataSource

data class EntityManagerFactoryConfiguration(
    val dataSource: DataSource,
    val packageToScan: String,
    val persistenceUnit: String,
    val jpaCustomProperties: JpaCustomProperties
) {
    fun toEntityManagerFactoryBean(): LocalContainerEntityManagerFactoryBean {
        val jpaVendorAdapter = jpaVendorAdapter(jpaCustomProperties.toJpaProperties())
        return EntityManagerFactoryBuilder(
            /* jpaVendorAdapter = */ jpaVendorAdapter,
            /* jpaPropertiesFactory = */ { jpaVendorAdapter.jpaPropertyMap },
            /* persistenceUnitManager = */ null
        ).dataSource(dataSource)
            .packages(packageToScan)
            .persistenceUnit(persistenceUnit)
            .build()
    }

    private fun jpaVendorAdapter(
        jpaProperties: JpaProperties
    ): AbstractJpaVendorAdapter = HibernateJpaVendorAdapter().apply {
        this.setDatabase(jpaProperties.database)
        this.setDatabasePlatform(jpaProperties.databasePlatform)
        this.setShowSql(jpaProperties.isShowSql)
        this.setGenerateDdl(jpaProperties.isGenerateDdl)
        this.jpaPropertyMap.putAll(jpaProperties.properties)
    }
}