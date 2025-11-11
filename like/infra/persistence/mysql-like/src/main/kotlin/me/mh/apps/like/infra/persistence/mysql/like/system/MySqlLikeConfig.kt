package me.mh.apps.like.infra.persistence.mysql.like.system

import com.zaxxer.hikari.HikariDataSource
import jakarta.persistence.EntityManagerFactory
import me.mh.apps.like.infra.persistence.mysql.like.system.constants.MySqlLikeConstants
import me.mh.apps.settings.infra.spring.persistence.datasource.HikariReplicationDataSourceConfigurationTemplate
import me.mh.apps.settings.infra.spring.persistence.datasource.ReplicationRoutingDataSource
import me.mh.apps.settings.infra.spring.persistence.jpa.system.JpaConfigurationTemplate
import me.mh.apps.settings.infra.spring.persistence.jpa.system.properties.JpaCustomProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
@ConfigurationPropertiesScan(MySqlLikeConstants.BASE_PACKAGE)
internal class MySqlLikeConfig :
    HikariReplicationDataSourceConfigurationTemplate,
    JpaConfigurationTemplate {

    override val packageToScan: String = MySqlLikeConstants.BASE_PACKAGE
    override val persistenceUnit: String = MySqlLikeConstants.PERSISTENCE_UNIT_NAME

    @Bean(MySqlLikeConstants.PRIMARY_DATA_SOURCE_NAME)
    @ConfigurationProperties("like.data-sources.replication.hikari.primary")
    override fun primary(): HikariDataSource = super.primary()

    @Bean(MySqlLikeConstants.SECONDARY_DATA_SOURCES_NAME)
    @ConfigurationProperties("like.data-sources.replication.hikari.secondaries")
    override fun secondary(): HikariDataSource = super.secondary()

    @Bean(MySqlLikeConstants.REPLICATION_DATA_SOURCE_NAME)
    override fun replicationDataSource(): ReplicationRoutingDataSource = super.replicationDataSource()

    @Bean(name = [MySqlLikeConstants.DATA_SOURCE_NAME, "datasource"])
    @Primary
    override fun dataSource(): LazyConnectionDataSourceProxy = super.dataSource()

    @Bean
    @ConfigurationProperties("like.jpa")
    override fun jpaCustomProperties(): JpaCustomProperties = JpaCustomProperties.mysql()

    @Bean(MySqlLikeConstants.ENTITY_MANAGER_FACTORY_NAME)
    override fun entityManagerFactory(dataSource: DataSource): LocalContainerEntityManagerFactoryBean =
        super.entityManagerFactory(dataSource)

    @Bean(name = [MySqlLikeConstants.TX_MANAGER_NAME, "transactionManager"])
    @Primary
    override fun jpaTransactionManager(
        entityManagerFactory: EntityManagerFactory
    ): JpaTransactionManager =
        super.jpaTransactionManager(entityManagerFactory)

    @Bean(MySqlLikeConstants.JDBC_TEMPLATE_NAME)
    fun likeJdbcTemplate(): NamedParameterJdbcTemplate =
        NamedParameterJdbcTemplate(dataSource())
}