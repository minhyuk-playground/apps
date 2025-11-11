plugins {
    `kotlin-dsl`
}

group = "me.mh"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

val kotlinVersion = springLibs.versions.kotlin.get()
val springBootVersion = springLibs.versions.springBoot.get()
val springDependencyManagementVersion = springLibs.versions.springDependencyManagement.get()
val jooqVersion = springLibs.versions.jooq.get()
val googleDevToolsKsp = springLibs.versions.google.devTools.ksp.get()

dependencies {
    // jetbrains
    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlin.kapt:org.jetbrains.kotlin.kapt.gradle.plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlin.plugin.spring:org.jetbrains.kotlin.plugin.spring.gradle.plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlin.plugin.jpa:org.jetbrains.kotlin.plugin.jpa.gradle.plugin:$kotlinVersion")

    // spring
    implementation("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
    implementation("io.spring.gradle:dependency-management-plugin:$springDependencyManagementVersion")

    // jooq
    implementation("org.jooq:jooq-meta-kotlin:$jooqVersion")
    implementation("org.jooq.jooq-codegen-gradle:org.jooq.jooq-codegen-gradle.gradle.plugin:$jooqVersion")

    // ksp
    runtimeOnly("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:$googleDevToolsKsp")

    // libs
    implementation(files(springLibs.javaClass.superclass.protectionDomain.codeSource.location))
}

tasks {
    wrapper {
        enabled = false
    }
}