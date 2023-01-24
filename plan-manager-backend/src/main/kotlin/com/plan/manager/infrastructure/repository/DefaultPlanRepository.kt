package com.plan.manager.infrastructure.repository

import com.plan.manager.domain.model.Plan
import com.plan.manager.domain.model.User
import com.plan.manager.domain.repository.PlanRepository
import com.plan.manager.domain.type.Address
import com.plan.manager.domain.type.StatusEnum
import com.plan.manager.domain.type.WhetherForecast
import com.plan.manager.infrastructure.database.mapper.PlanMapper
import org.springframework.stereotype.Repository

/**
 * プランリポジトリ実装クラス
 */
@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class DefaultPlanRepository (
    private val planMapper: PlanMapper
    ): PlanRepository {
    override fun findAllWithWhetherForecast(): List<Plan> {
        return listOf(
            Plan(
            1,
            User(
                1,
                "kensho"
            ),
            "test",
            "test",
            Address.of("東京都"),
                "2023-01-22T12:00:00.000+09:00",
                "2023-01-22T16:00:00.000+09:00",
                StatusEnum.COMPLETE,
                WhetherForecast.createdNotWhetherForecast()
            )
        )
    }
}