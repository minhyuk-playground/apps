plugins {
    alias(springConventionLibs.plugins.spring.jpa)
}

group = "me.mh.apps.settings.support-spring.persistence"

dependencies {
    api(project(":persistence:datasource"))
}