/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2023-01-23T23:24:19.027708+09:00
 */
package com.plan.manager.infrastructure.database.record

import java.time.LocalDateTime

data class PlanRecord(
    var id: Long? = null,
    var userId: Long? = null,
    var title: String? = null,
    var description: String? = null,
    var address: String? = null,
    var startDate: LocalDateTime? = null,
    var endDate: LocalDateTime? = null,
    var status: String? = null
)