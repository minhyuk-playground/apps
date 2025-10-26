package me.mh.apps.settings.plugins.spring

import org.gradle.kotlin.dsl.kotlin

plugins {
    id("me.mh.apps.settings.plugins.spring.boot")
    kotlin("plugin.jpa")
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

dependencies {
    api("org.springframework:spring-tx")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}