package com.plan.manager.infrastructure.repository

import com.plan.manager.domain.model.Plan
import com.plan.manager.domain.model.User
import com.plan.manager.domain.repository.PlanRepository
import com.plan.manager.domain.type.Address
import com.plan.manager.domain.type.StatusEnum
import com.plan.manager.infrastructure.database.mapper.PlanMapper
import org.springframework.stereotype.Repository
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
            Address.of("東京都"),
                    Date(),
                    Date(),
                StatusEnum.COMPLETE,
            )
        )
    }
}