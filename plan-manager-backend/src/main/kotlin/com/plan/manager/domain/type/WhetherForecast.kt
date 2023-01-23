package com.plan.manager.domain.type

import java.util.Date

data class WhetherForecast(
    val tem: Temperature,
    val sunrise: Date,
    val sunset: Date,
    val whether: String
) {
    companion object {
        /**
         * 予報情報がない場合に返す。
         */
        fun createdNotWhetherForecast(): WhetherForecast {
            return WhetherForecast(
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
