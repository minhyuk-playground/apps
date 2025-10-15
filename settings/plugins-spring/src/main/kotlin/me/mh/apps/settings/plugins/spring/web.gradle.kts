package me.mh.apps.settings.plugins.spring

import gradle.kotlin.dsl.accessors._938f221190fafc3c2a2646bedc3a3cf2.bootJar

plugins {
    id("me.mh.apps.settings.plugins.spring.boot")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
}

tasks {
    jar {
        enabled = false
    }

    bootJar {
        enabled = true
    }
}