plugins {
    alias(springConventionLibs.plugins.spring.boot)
}

group = "me.mh.apps.settings.support-spring.persistence"

dependencies {
    api("org.springframework:spring-tx")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
}