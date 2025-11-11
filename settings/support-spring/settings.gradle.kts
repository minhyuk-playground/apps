rootProject.name = "support-spring"


dependencyResolutionManagement {
    versionCatalogs {
        create("springLibs") {
            from(files("../versions/spring/libs.versions.toml"))
        }
    }
}

pluginManagement {
    includeBuild("../plugins-spring")
}

include(
    ":persistence:datasource",
    ":persistence:jpa",
)