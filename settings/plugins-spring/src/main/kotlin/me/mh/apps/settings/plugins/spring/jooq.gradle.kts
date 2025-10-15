package me.mh.apps.settings.plugins.spring

plugins {
    id("me.mh.apps.settings.plugins.spring.boot")
    id("org.jooq.jooq-codegen-gradle")
}

dependencies {
    val jooqVersion = dependencyManagement.importedProperties["jooq.version"]
    implementation("org.jooq:jooq-kotlin:$jooqVersion")
}

jooq {
    configuration {
        logging = org.jooq.meta.jaxb.Logging.WARN

        jdbc {
            url = "각 모듈에서 알맞게 입력해주세요"
            driver = "각 모듈에서 알맞게 입력해주세요"
            username = "각 모듈에서 알맞게 입력해주세요"
            password = "각 모듈에서 알맞게 입력해주세요"
        }

        generator {
            name = "org.jooq.codegen.KotlinGenerator"

            database {
                name = "각 모듈에서 알맞게 입력해주세요(ex : org.jooq.meta.mysql.MySQLDatabase)"
                inputSchema = "각 모듈에서 알맞게 입력해주세요"
            }

            generate {
                deprecated = false
                isDaos = true
                isPojos = true
                isPojosAsKotlinDataClasses = true
            }

            target {
                packageName = "각 모듈에서 알맞게 입력해주세요.(me.mh.apps.jooq)"
                directory = "src/main/kotlin"
            }
        }
    }
}