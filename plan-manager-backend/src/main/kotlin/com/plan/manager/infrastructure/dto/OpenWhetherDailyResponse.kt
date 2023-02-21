package com.plan.manager.infrastructure.dto

data class OpenWhetherDailyResponse(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: String,
    val daily: List<OpenWhetherDailyElements>
)
