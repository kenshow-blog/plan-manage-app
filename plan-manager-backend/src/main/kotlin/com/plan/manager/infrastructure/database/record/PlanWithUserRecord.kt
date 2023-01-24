package com.plan.manager.infrastructure.database.record

data class PlanWithUserRecord(
    var id: Long? = null,
    var userId: Long? = null,
    var userName: String? = null,
    var title: String? = null,
    var description: String? = null,
    var address: String? = null,
    var start_date: String? = null,
    var end_date: String? = null,
    val status: String? = null
)
