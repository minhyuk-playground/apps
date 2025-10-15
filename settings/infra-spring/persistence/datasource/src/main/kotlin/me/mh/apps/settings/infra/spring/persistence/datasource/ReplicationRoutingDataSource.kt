package me.mh.apps.settings.infra.spring.persistence.datasource

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.transaction.support.TransactionSynchronizationManager
import java.util.concurrent.atomic.AtomicInteger
import javax.sql.DataSource

class ReplicationRoutingDataSource(
    val primaryId: String,
    dataSources: Map<String, DataSource>
) : AbstractRoutingDataSource() {
    private val secondaryIds: List<String> = dataSources.keys.filterNot { it == primaryId }
    private val nextIdIndex = AtomicInteger(0)

    init {
        validate(dataSources)
        super.setDefaultTargetDataSource(dataSources[primaryId]!!)
        super.setTargetDataSources(dataSources.toMap())
        afterPropertiesSet()
    }

    override fun setDefaultTargetDataSource(defaultTargetDataSource: Any) {
        throw UnsupportedOperationException()
    }

    override fun setTargetDataSources(targetDataSources: Map<in Any, Any?>) {
        throw UnsupportedOperationException()
    }

    override fun determineCurrentLookupKey(): Any {
        if (!TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            return primaryId
        }
        return routeToSecondary()
    }

    private fun routeToSecondary(): String {
        if (secondaryIds.isEmpty()) {
            return primaryId
        }
        val currentIndex = nextIdIndex.getAndUpdate { (it + 1) % secondaryIds.size }
        return secondaryIds[currentIndex]
    }

    private fun validate(dataSources: Map<String, DataSource>) {
        check(primaryId.isNotBlank()) { "primaryId must not be blank." }
        check(secondaryIds.isNotEmpty()) { "secondaryIds must not be empty." }

        check(dataSources.isNotEmpty()) { "dataSources must not be empty." }
        check(dataSources.containsKey(primaryId)) { "dataSources must contain primaryId." }
        check(dataSources.values.distinct().size == dataSources.size) { "dataSource is duplicated." }

        dataSources.keys.forEach {
            check(it.isNotBlank()) { "dataSourceId must not be blank." }
        }
    }
}