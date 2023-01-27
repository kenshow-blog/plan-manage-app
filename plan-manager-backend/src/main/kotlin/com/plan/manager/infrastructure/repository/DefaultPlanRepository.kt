package com.plan.manager.infrastructure.repository

import com.plan.manager.domain.model.Plan
import com.plan.manager.domain.model.User
import com.plan.manager.domain.repository.PlanRepository
import com.plan.manager.domain.type.Prefecture
import com.plan.manager.domain.type.StatusEnum
import com.plan.manager.infrastructure.database.mapper.PlanMapper
import com.plan.manager.infrastructure.database.mapper.deleteByPrimaryKey
import com.plan.manager.infrastructure.database.mapper.insert
import com.plan.manager.infrastructure.database.mapper.updateByPrimaryKeySelective
import com.plan.manager.infrastructure.database.record.PlanRecord
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*

/**
 * プランリポジトリ実装クラス
 */
@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class DefaultPlanRepository (
    private val planMapper: PlanMapper
    ): PlanRepository {
    override fun findAllWithUser(): List<Plan> {
        return listOf(
            Plan.of(
            1,
            User(
                1,
                "kensho"
            ),
            "test",
            "test",
            Prefecture.of("東京都"),
                    Date(),
                    Date(),
                StatusEnum.COMPLETE,
            )
        )
    }

    override fun save(
            id: Long,
            userId: Long,
            title: String,
            description: String,
            prefecture: Prefecture,
            startDate: LocalDateTime,
            endDate: LocalDateTime,
            status: StatusEnum ) {
        planMapper.insert(
                toRecord(
                        id,
                    userId,
                    title,
                    description,
                    prefecture,
                    startDate,
                    endDate,
                    status
                )
        )
    }

    override fun update(id: Long, title: String?, description: String?, prefecture: Prefecture?, startDate: LocalDateTime?, endDate: LocalDateTime?, status: StatusEnum?) {
        planMapper.updateByPrimaryKeySelective(toRecord(id, title= title, description = description, prefecture = prefecture, startDate = startDate, endDate = endDate, status = status))
    }

    override fun delete(id: Long) {
        planMapper.deleteByPrimaryKey(id)
    }

    private fun toRecord(id: Long,
                         userId: Long? = null,
                         title: String?,
                         description: String?,
                         prefecture: Prefecture?,
                         startDate: LocalDateTime?,
                         endDate: LocalDateTime?,
                         status: StatusEnum?): PlanRecord {
        return PlanRecord(
                id,
                userId,
                title,
                description,
                prefecture?.value?.name,
                startDate,
                endDate,
                status.toString(),
               )
    }
}