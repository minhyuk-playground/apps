rootProject.name = "apps"

dependencyResolutionManagement {
    versionCatalogs {
        create("springConventionLibs") {
            from(files("./settings/versions/spring/convention.versions.toml"))
        }
    }
}

includeBuild("./settings/plugins-spring")
includeBuild("./settings/support-spring")

include(
    ":like:core",
    ":like:infra:persistence:mysql-like",
    ":like:apps:batch",
    ":like:apps:customer-api",
)