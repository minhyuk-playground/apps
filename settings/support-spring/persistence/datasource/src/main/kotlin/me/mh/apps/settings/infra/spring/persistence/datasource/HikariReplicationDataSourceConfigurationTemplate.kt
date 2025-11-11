package me.mh.apps.settings.infra.spring.persistence.datasource

import com.zaxxer.hikari.HikariDataSource
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import org.springframework.transaction.PlatformTransactionManager

interface HikariReplicationDataSourceConfigurationTemplate {
    fun primary(): HikariDataSource = HikariDataSource()

    fun secondary(): HikariDataSource = HikariDataSource()

    fun replicas(): Map<String, HikariDataSource> = mutableMapOf()

    fun replicationDataSource(): ReplicationRoutingDataSource = ReplicationRoutingDataSource(
        primaryId = primary().poolName,
        dataSources = mapOf(
            primary().poolName to primary(),
            secondary().poolName to secondary()
        )
    )

    fun dataSource(): LazyConnectionDataSourceProxy = LazyConnectionDataSourceProxy(replicationDataSource())

    fun transactionManager(): PlatformTransactionManager = DataSourceTransactionManager(dataSource())
}