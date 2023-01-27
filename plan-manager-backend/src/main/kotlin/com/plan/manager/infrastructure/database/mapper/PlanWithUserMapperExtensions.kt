package com.plan.manager.infrastructure.database.mapper
import com.plan.manager.infrastructure.database.mapper.PlanDynamicSqlSupport.Plan
import com.plan.manager.infrastructure.database.mapper.PlanDynamicSqlSupport.Plan.prefecture
import com.plan.manager.infrastructure.database.mapper.PlanDynamicSqlSupport.Plan.description
import com.plan.manager.infrastructure.database.mapper.PlanDynamicSqlSupport.Plan.endDate
import com.plan.manager.infrastructure.database.mapper.PlanDynamicSqlSupport.Plan.id
import com.plan.manager.infrastructure.database.mapper.PlanDynamicSqlSupport.Plan.startDate
import com.plan.manager.infrastructure.database.mapper.PlanDynamicSqlSupport.Plan.status
import com.plan.manager.infrastructure.database.mapper.PlanDynamicSqlSupport.Plan.title
import com.plan.manager.infrastructure.database.mapper.PlanDynamicSqlSupport.Plan.userId
import com.plan.manager.infrastructure.database.mapper.UserDynamicSqlSupport.User.name
import com.plan.manager.infrastructure.database.mapper.UserDynamicSqlSupport.User
import com.plan.manager.infrastructure.database.record.PlanWithUserRecord
import org.mybatis.dynamic.sql.SqlBuilder.equalTo
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

private val columnList = listOf(
    id,
    userId,
    name,
    title,
    description,
    prefecture,
    startDate,
    endDate,
    status
)

fun PlanWithUserMapper.select(): List<PlanWithUserRecord> {
    val selectStatement = select(columnList) {
        from(Plan, "p")
        leftJoin(User, "u") {
            on(Plan.userId, equalTo(User.id))
        }
    }
    return selectMany(selectStatement)
}