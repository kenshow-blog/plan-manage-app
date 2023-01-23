package com.plan.manager.domain.model

import com.plan.manager.domain.type.Address
import com.plan.manager.domain.type.StatusEnum
import com.plan.manager.domain.type.WhetherForecast
import java.time.LocalDateTime

/**
 * 予定クラス
 */
data class Plan(
    val id: Long,
    val userId: Long,
    val title: String,
    val description: String,
    val address: Address,
    val start_date: String,
    val end_date: String,
    val status: StatusEnum,
    val whetherForecast: WhetherForecast
)
