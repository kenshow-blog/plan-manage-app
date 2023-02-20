package com.plan.manager.presentation.dto

import com.plan.manager.domain.type.Temperature
import java.time.LocalDateTime
import java.util.*

/**
 * 予定リストのDTO
 */
data class GetPlanListResponse(val planList: List<PlanElements>)
/**
 * 削除した予定のDTO
 */
data class DeletePlanId(val id: Long)
/**
 * 予定要素
 */
data class PlanElements (
        val id: Long,
        val user: UserElements,
        val title: String,
        val description: String,
        val prefecture: String,
        val start_date: LocalDateTime,
        val end_date: LocalDateTime,
        val status: String,
        val whether: WhetherElements?
)

/**
 * ユーザー要素
 */
data class UserElements (
        val id: Long,
        val name: String?
)

/**
 * 天気要素
 */
data class WhetherElements (
        val dt: Date,
        val tem: TemperatureElements,
        val sunrise: Date,
        val sunset: Date,
        val whether: String
)

/**
 * 温度要素
 */
data class TemperatureElements (
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double,
) {
    constructor(model: Temperature) : this(
            model.day,
            model.min,
            model.max,
            model.night,
            model.eve,
            model.morn
    )
}