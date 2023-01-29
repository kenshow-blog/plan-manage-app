package com.plan.manager.infrastructure.dto

data class OpenWhetherDailyResponse(
        val lat: Double,
        val lon: Double,
        val timezone: String,
        val timezoneOffset: String,
        val daily: List<OpenWhetherDailyElements>
)
