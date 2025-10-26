package me.mh.apps.settings.plugins.spring

plugins {
    id("me.mh.apps.settings.plugins.spring.boot")
}

dependencies {
    val querydslVersion = dependencyManagement.importedProperties["querydsl.version"]
    implementation("com.querydsl:querydsl-apt:$querydslVersion:jakarta")
    implementation("com.querydsl:querydsl-jpa:$querydslVersion:jakarta")
}