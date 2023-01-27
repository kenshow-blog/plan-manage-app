/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2023-01-27T20:46:43.521387+09:00
 */
package com.plan.manager.infrastructure.database.mapper

import java.sql.JDBCType
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.SqlTable

object PlanDynamicSqlSupport {
    object Plan : SqlTable("public.plan") {
        val id = column<Long>("id", JDBCType.BIGINT)

        val userId = column<Long>("user_id", JDBCType.BIGINT)

        val title = column<String>("title", JDBCType.VARCHAR)

        val description = column<String>("description", JDBCType.VARCHAR)

        val prefecture = column<String>("prefecture", JDBCType.VARCHAR)

        val startDate = column<LocalDateTime>("start_date", JDBCType.TIMESTAMP)

        val endDate = column<LocalDateTime>("end_date", JDBCType.TIMESTAMP)

        val status = column<String>("status", JDBCType.VARCHAR)
    }
}