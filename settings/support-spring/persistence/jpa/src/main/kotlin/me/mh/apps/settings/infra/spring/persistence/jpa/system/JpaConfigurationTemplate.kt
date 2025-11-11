package me.mh.apps.settings.infra.spring.persistence.jpa.system

import jakarta.persistence.EntityManagerFactory
import me.mh.apps.settings.infra.spring.persistence.jpa.system.properties.JpaCustomProperties
import org.hibernate.cfg.AvailableSettings
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.sql.DataSource

interface JpaConfigurationTemplate {
    val packageToScan: String
    val persistenceUnit: String

    fun jpaCustomProperties(): JpaCustomProperties = JpaCustomProperties.mysql()

    fun entityManagerFactory(dataSource: DataSource): LocalContainerEntityManagerFactoryBean =
        jpaVendorAdapter(jpaCustomProperties().toJpaProperties()).let { adapter ->
            EntityManagerFactoryBuilder(
                adapter,
                { adapter.jpaPropertyMap },
                null
            ).dataSource(dataSource)
                .packages(packageToScan)
                .persistenceUnit(persistenceUnit)
                .build()
        }

    fun jpaTransactionManager(entityManagerFactory: EntityManagerFactory): JpaTransactionManager =
        JpaTransactionManager(entityManagerFactory)

    private fun jpaVendorAdapter(
        jpaProperties: JpaProperties
    ): AbstractJpaVendorAdapter = HibernateJpaVendorAdapter().apply {
        this.setShowSql(jpaProperties.isShowSql)
        this.setGenerateDdl(jpaProperties.isGenerateDdl)
        this.jpaPropertyMap.putAll(createJpaOptions(jpaProperties))
    }

    private fun createJpaOptions(jpaProperties: JpaProperties): Map<String, Any> =
        HibernateProperties().apply {
            this.ddlAuto = jpaProperties.properties[AvailableSettings.HBM2DDL_AUTO]
            this.naming.implicitStrategy = jpaProperties.properties[AvailableSettings.IMPLICIT_NAMING_STRATEGY]
            this.naming.physicalStrategy = jpaProperties.properties[AvailableSettings.PHYSICAL_NAMING_STRATEGY]
        }.determineHibernateProperties(jpaProperties.properties, HibernateSettings())
}