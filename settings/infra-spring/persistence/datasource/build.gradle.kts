plugins {
    alias(springConventionLibs.plugins.spring.boot)
}

dependencies {
    api("org.springframework:spring-tx")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
}