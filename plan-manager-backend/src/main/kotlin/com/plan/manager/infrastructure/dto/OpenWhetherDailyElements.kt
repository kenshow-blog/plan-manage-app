package com.plan.manager.infrastructure.dto

class OpenWhetherDailyElements (
        val dt: Long,
        val sunrise: Long,
        val sunset: Long,
        val moonrise: Long,
        val moonset: Long,
        val moon_phase: Double,
        val temp: OpenWhetherDailyTempElements,
        val feels_like: OpenWhetherDailyFeelsLikeElements,
        val pressure: Long,
        val humidity: Long,
        val dew_point: Double,
        val wind_speed: Double,
        val wind_deg: Double,
        val wind_gust: Double,
        val weather: List<OpenWhetherDailyWhetherElements>,
        val clouds: Double,
        val pop: Double,
        val snow: Double,
        val uvi: Double,
)