package com.plan.manager.domain.model

import com.plan.manager.domain.type.Temperature
import java.util.Date

/**
 * 天気予報エンティティ
 */
data class Weather(
        val dt: Date,
        val tem: Temperature,
        val sunrise: Date,
        val sunset: Date,
        val whether: String
)
