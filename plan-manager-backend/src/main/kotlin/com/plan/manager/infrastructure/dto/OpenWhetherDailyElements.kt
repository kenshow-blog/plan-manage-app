package com.plan.manager.infrastructure.dto

class OpenWhetherDailyElements (
        val dt: Long,
        val sunrise: Long,
        val sunset: Long,
        val moonrise: Long,
        val moonset: Long,
        val moonPhase: Double,
        val temp: OpenWhetherDailyTempElements,
        val feelsLike: OpenWhetherDailyFeelsLikeElements,
        val pressure: Long,
        val humidity: Long,
        val dewPoint: Double,
        val windSpeed: Double,
        val windDeg: Double,
        val windGust: Double,
        val whether: List<OpenWhetherDailyWhetherElements>,
        val clouds: Double,
        val pop: Double,
        val snow: Double,
        val uvi: Double,
)