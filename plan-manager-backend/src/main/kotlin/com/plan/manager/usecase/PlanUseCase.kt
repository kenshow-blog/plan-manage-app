package com.plan.manager.usecase

import com.plan.manager.domain.model.Plan
import com.plan.manager.domain.repository.PlanRepository
import com.plan.manager.domain.repository.WhetherRepository

/**
 * 予定ユースケース
 */
class PlanUseCase(
        private val planRepository: PlanRepository,
        private val whetherRepository: WhetherRepository
) {
    fun getList(): List<Plan> {
        val plans = planRepository.findAllWithUser()
        val whetherForecasts = whetherRepository.getForecastsWithin8Days()
        plans.forEach {
            it.addWhetherInfo(whetherForecasts)
        }
        return plans
    }
}