package com.plan.manager.infrastructure.repository

import com.plan.manager.domain.model.Plan
import com.plan.manager.domain.model.User
import com.plan.manager.domain.repository.PlanRepository
import com.plan.manager.domain.type.Prefecture
import com.plan.manager.domain.type.StatusEnum
import com.plan.manager.infrastructure.database.mapper.*
import com.plan.manager.infrastructure.database.record.PlanRecord
import org.springframework.stereotype.Repository
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

/**
 * プランリポジトリ実装クラス
 */
@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class DefaultPlanRepository (
    private val planMapper: PlanMapper,
    private val planWithUserMapper: PlanWithUserMapper
    ): PlanRepository {
    override fun findAllWithUser(): List<Plan> {
        return planWithUserMapper.select().map {
            Plan.of(
                    it.id!!,
                    User(
                            it.userId!!,
                            it.userName!!
                    ),
                    it.title!!,
                    it.description!!,
                    Prefecture.of(it.prefecture!!),
                    it.start_date!!,
                    it.end_date!!,
                    StatusEnum.getStatus(it.status!!),
            )
        }
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