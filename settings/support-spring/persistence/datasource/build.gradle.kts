plugins {
    alias(springLibs.plugins.me.spring.boot)
}

group = "me.mh.apps.settings.support-spring.persistence"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
}