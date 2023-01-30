package com.plan.manager.infrastructure.database.record

import java.time.LocalDateTime

data class PlanWithUserRecord(
        var id: Long? = null,
        var userId: Long? = null,
        var userName: String? = null,
        var title: String? = null,
        var description: String? = null,
        var prefecture: String? = null,
        var startDate: LocalDateTime? = null,
        var endDate: LocalDateTime? = null,
        val status: String? = null
)
