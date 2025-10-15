rootProject.name = "apps"

includeBuild("./settings/plugins-spring")
includeBuild("./settings/infra-spring")

include(
    ":like:core",
    ":like:batch",
    ":like:customer-api",
)