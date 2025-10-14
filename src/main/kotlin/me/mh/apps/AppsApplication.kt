package me.mh.apps

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AppsApplication

fun main(args: Array<String>) {
    runApplication<AppsApplication>(*args)
}
