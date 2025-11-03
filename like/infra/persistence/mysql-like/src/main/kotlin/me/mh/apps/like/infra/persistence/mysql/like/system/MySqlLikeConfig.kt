package me.mh.apps.like.infra.persistence.mysql.like.system

import com.zaxxer.hikari.HikariDataSource
import me.mh.apps.like.infra.persistence.mysql.like.system.constants.MySqlLikeConstants
import me.mh.apps.settings.infra.spring.persistence.datasource.ReplicationRoutingDataSource
import me.mh.apps.settings.infra.spring.persistence.jpa.system.EntityManagerFactoryConfiguration
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

@Configuration
@ConfigurationPropertiesScan(MySqlLikeConstants.BASE_PACKAGE)
internal class MySqlLikeConfig {

    @Bean(MySqlLikeConstants.PRIMARY_DATA_SOURCE_NAME)
    @ConfigurationProperties("like.data-sources.replication.hikari.primary")
    fun likeMySqlPrimaryDataSource(): HikariDataSource = HikariDataSource()

    @Bean(MySqlLikeConstants.SECONDARY_DATA_SOURCES_NAME)
    @ConfigurationProperties("like.data-sources.replication.hikari.secondaries")
    fun likeMySqlSecondaryDataSources(): List<HikariDataSource> = mutableListOf()

    @Bean(MySqlLikeConstants.REPLICATION_DATA_SOURCE_NAME)
    fun likeMySqlReplicationDataSource(): ReplicationRoutingDataSource =
        ReplicationRoutingDataSource(
            primaryId = likeMySqlPrimaryDataSource().poolName,
            dataSources = likeMySqlSecondaryDataSources().associateBy { it.poolName }
        )

    @Bean(name = [MySqlLikeConstants.DATA_SOURCE_NAME, "datasource"])
    @Primary
    fun likeMySqlDataSource(): LazyConnectionDataSourceProxy =
        LazyConnectionDataSourceProxy(likeMySqlReplicationDataSource())

    @Bean
    @ConfigurationProperties("like.jpa")
    fun likeJpaCustomProperties(): JpaCustomProperties = JpaCustomProperties.mysql()

    @Bean(MySqlLikeConstants.ENTITY_MANAGER_FACTORY_NAME)
    fun likeEntityManagerFactory(): LocalContainerEntityManagerFactoryBean =
        EntityManagerFactoryConfiguration(
            dataSource = likeMySqlReplicationDataSource(),
            packageToScan = MySqlLikeConstants.BASE_PACKAGE,
            persistenceUnit = MySqlLikeConstants.PERSISTENCE_UNIT_NAME,
            jpaCustomProperties = likeJpaCustomProperties()
        ).toEntityManagerFactoryBean()

    @Bean(name = [MySqlLikeConstants.TX_MANAGER_NAME, "transactionManager"])
    fun likeTransactionManager(): PlatformTransactionManager =
        JpaTransactionManager(likeEntityManagerFactory().`object`!!)

    @Bean(MySqlLikeConstants.JDBC_TEMPLATE_NAME)
    fun likeJdbcTemplate(): NamedParameterJdbcTemplate =
        NamedParameterJdbcTemplate(likeMySqlDataSource())
}