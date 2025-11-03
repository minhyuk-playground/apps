package me.mh.apps.like.infra.persistence.mysql.like.system.constants

object MySqlLikeConstants {
    internal const val BASE_PACKAGE = "me.mh.apps.like.infra.persistence.mysql.like"
    internal const val PERSISTENCE_UNIT_NAME = "db-like"
    internal const val PRIMARY_DATA_SOURCE_NAME = "likeMySqlPrimaryDataSource"
    internal const val SECONDARY_DATA_SOURCES_NAME = "likeMySqlSecondaryDataSources"
    internal const val REPLICATION_DATA_SOURCE_NAME = "likeMySqlReplicationDataSource"
    internal const val DATA_SOURCE_NAME = "likeMySqlDataSource"
    internal const val ENTITY_MANAGER_FACTORY_NAME = "likeEntityManagerFactory"
    internal const val TX_MANAGER_NAME = "likeTransactionManager"
    internal const val JDBC_TEMPLATE_NAME = "likeJdbcTemplate"
}