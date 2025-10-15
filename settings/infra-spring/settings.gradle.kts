rootProject.name = "infra-spring"

includeBuild("../plugins-spring")

dependencyResolutionManagement {
    versionCatalogs {
        create("springConventionLibs") {
            from(files("../versions/spring/convention.versions.toml"))
        }
    }
}


include(
    ":persistence:datasource",
    ":persistence:jpa",
)