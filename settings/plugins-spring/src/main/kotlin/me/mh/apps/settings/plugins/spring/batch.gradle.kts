package me.mh.apps.settings.plugins.spring

plugins {
    id("me.mh.apps.settings.plugins.spring.boot")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-batch")
    testImplementation("org.springframework.batch:spring-batch-test")
}

tasks {
    jar {
        enabled = false
    }

    bootJar {
        enabled = true
    }
}