package com.plan.manager.infrastructure.database.mapper

import com.plan.manager.infrastructure.database.record.PlanWithUserRecord
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter

/**
 * 自作 プラン&ユーザーマッパークラス
 */
@Mapper
interface PlanWithUserMapper {
    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @Results(
        id = "PlanWithUserRecordResult", value = [
            Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            Result(column = "user_id", property = "userId", jdbcType = JdbcType.BIGINT),
            Result(column = "name", property = "userName", jdbcType = JdbcType.VARCHAR),
            Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            Result(column = "prefecture", property = "prefecture", jdbcType = JdbcType.VARCHAR),
            Result(column = "start_date", property = "start_date", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "end_date", property = "end_date", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "status", property = "status", jdbcType = JdbcType.VARCHAR),
        ]
    )
    fun selectMany(selectStatement: SelectStatementProvider): List<PlanWithUserRecord>
}