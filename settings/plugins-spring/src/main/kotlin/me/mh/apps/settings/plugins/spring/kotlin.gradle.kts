package me.mh.apps.settings.plugins.spring


plugins {
    kotlin("jvm")
    id("io.spring.dependency-management")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
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
    test { useJUnitPlatform() }
    jar { enabled = true }
}