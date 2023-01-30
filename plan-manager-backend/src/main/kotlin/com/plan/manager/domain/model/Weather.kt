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
) {
    companion object {
        /**
         * 予報情報がない場合に返す。
         */
        fun createdNotWhetherForecast(): Weather {
            return Weather(
                    Date(),
                Temperature(
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                ),
                Date(),
                Date(),
                "晴天"
            )
        }
    }
}
