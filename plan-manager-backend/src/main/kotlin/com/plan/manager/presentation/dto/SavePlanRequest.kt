package com.plan.manager.presentation.dto

import java.time.LocalDateTime

/**
 * 予定作成DTOクラス
 */
data class SavePlanRequest(
    val userId: Long,
    val title: String,
    val description: String,
    val prefecture: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val status: String
)
