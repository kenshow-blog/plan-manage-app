package com.plan.manager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PlanManagerBackendApplication

fun main(args: Array<String>) {
    runApplication<PlanManagerBackendApplication>(*args)
}
