package me.mh.apps.settings.plugins.spring

import org.gradle.accessors.dm.LibrariesForSpringLibs
import org.gradle.kotlin.dsl.the

plugins {
    kotlin("jvm")
    id("com.google.devtools.ksp")
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

val springLibs = the<LibrariesForSpringLibs>()

dependencies {
    val queryDslVersion = springLibs.versions.openfeign.querydsl.get()
    implementation("io.github.openfeign.querydsl:querydsl-jpa:$queryDslVersion")
    ksp("io.github.openfeign.querydsl:querydsl-ksp-codegen:$queryDslVersion")
}