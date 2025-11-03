package me.mh.apps.settings.plugins.spring

import gradle.kotlin.dsl.accessors._160bcc4ee9483fe4d5a2d8de58fe9f02.testImplementation
import gradle.kotlin.dsl.accessors._160bcc4ee9483fe4d5a2d8de58fe9f02.testRuntimeOnly


plugins {
    kotlin("jvm")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

repositories {
    mavenCentral()
}

dependencies {
    // TODO("Junit5 의존성으로 수정")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}


kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks {
    test {
        useJUnitPlatform()
    }

    jar {
        enabled = true
    }

    bootJar {
        enabled = false
    }
}