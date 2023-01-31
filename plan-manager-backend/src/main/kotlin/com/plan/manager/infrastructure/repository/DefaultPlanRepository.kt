package com.plan.manager.infrastructure.repository

import com.plan.manager.domain.model.Plan
import com.plan.manager.domain.model.User
import com.plan.manager.domain.repository.PlanRepository
import com.plan.manager.domain.type.Prefecture
import com.plan.manager.domain.type.StatusEnum
import com.plan.manager.infrastructure.database.mapper.*
import com.plan.manager.infrastructure.database.record.PlanRecord
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

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
                    it.startDate!!,
                    it.endDate!!,
                    StatusEnum.getStatus(it.status!!),
            )
        }
    }

    override fun save( plan: Plan ) {
        planMapper.insert(
                toRecord(
                        plan.id,
                        plan.user.id,
                        plan.title,
                        plan.description,
                        plan.prefecture,
                        plan.startDate,
                        plan.endDate,
                        plan.status
                )
        )
    }

    override fun update(id: Long, title: String?, description: String?, prefecture: Prefecture?, startDate: LocalDateTime?, endDate: LocalDateTime?, status: StatusEnum?) {
        planMapper.updateByPrimaryKeySelective(toRecord(id, title= title, description = description, prefecture = prefecture, startDate = startDate, endDate = endDate, status = status))
    }

    override fun delete(id: Long) {
        planMapper.deleteByPrimaryKey(id)
    }

    override fun findOne(id: Long): Plan? {
        val plan = planWithUserMapper.selectByPrimaryKey(id)
        plan?.let {
            return Plan.of(
                    it.id!!,
                    User(
                            it.userId!!,
                            it.userName!!
                    ),
                    it.title!!,
                    it.description!!,
                    Prefecture.of(it.prefecture!!),
                    it.startDate!!,
                    it.endDate!!,
                    StatusEnum.getStatus(it.status!!),
            )
        } ?: run {
            return null
        }
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
                prefecture?.value?.prefecture,
                startDate,
                endDate,
                status.toString(),
               )
    }
}