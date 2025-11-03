plugins {
    alias(springConventionLibs.plugins.spring.jpa)
    alias(springConventionLibs.plugins.querydsl)
}

dependencies {
    compileOnly(project(":like:core"))
    implementation(springConventionLibs.spring.jpa)

    runtimeOnly("com.mysql:mysql-connector-j")
    testRuntimeOnly("com.h2database:h2")
}