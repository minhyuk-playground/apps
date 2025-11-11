rootProject.name = "apps"

dependencyResolutionManagement {
    versionCatalogs {
        create("springLibs") {
            from(files("./settings/versions/spring/libs.versions.toml"))
        }
    }
}

pluginManagement {
    includeBuild("./settings/plugins-spring")
}

includeBuild("./settings/support-spring")

include(
    ":like:core",
    ":like:infra:persistence:mysql-like",
    ":like:apps:batch",
    ":like:apps:customer-api",
)