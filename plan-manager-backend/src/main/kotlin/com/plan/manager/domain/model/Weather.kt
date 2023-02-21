package com.plan.manager.domain.model

import com.plan.manager.domain.type.Temperature
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

/**
 * 天気予報エンティティ
 */
data class Weather(
    val dt: LocalDateTime,
    val tem: Temperature,
    val sunrise: LocalDateTime,
    val sunset: LocalDateTime,
    val icon: String,
    val whether: String
) {
    companion object {
        fun of(dt: Date, tem: Temperature, sunrise: Date, sunset: Date, icon: String, weather: String): Weather {
            return Weather(
                toLocalDateTime(dt),
                tem,
                toLocalDateTime(sunrise),
                toLocalDateTime(sunset),
                icon,
                weather
            )
        }
        private fun toLocalDateTime(date: Date): LocalDateTime {
            val instant = date.toInstant()
            return LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Tokyo"))
        }
    }
}
