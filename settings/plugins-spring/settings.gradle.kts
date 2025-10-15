pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("springLibs") {
            from(files("../versions/spring/libs.versions.toml"))
        }
    }
}