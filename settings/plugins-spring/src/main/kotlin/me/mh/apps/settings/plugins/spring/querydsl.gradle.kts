import gradle.kotlin.dsl.accessors._938f221190fafc3c2a2646bedc3a3cf2.dependencyManagement
import gradle.kotlin.dsl.accessors._938f221190fafc3c2a2646bedc3a3cf2.implementation

plugins {
    id("me.mh.apps.settings.plugins.spring.boot")
}

dependencies {
    val querydslVersion = dependencyManagement.importedProperties["querydsl.version"]
    implementation("com.querydsl:querydsl-apt:$querydslVersion:jakarta")
    implementation("com.querydsl:querydsl-jpa:$querydslVersion:jakarta")
}