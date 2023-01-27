package com.plan.manager.usecase

import com.plan.manager.domain.model.Plan
import com.plan.manager.domain.model.User
import com.plan.manager.domain.repository.PlanRepository
import com.plan.manager.domain.repository.WhetherRepository
import com.plan.manager.domain.type.Prefecture
import com.plan.manager.domain.type.StatusEnum
import com.plan.manager.presentation.dto.SavePlanRequest
import com.plan.manager.presentation.dto.UpdatePlanRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

/**
 * 予定ユースケース
 */
@Service
class PlanUseCase(
        private val planRepository: PlanRepository,
        private val whetherRepository: WhetherRepository
) {
    @Transactional
    fun getList(): List<Plan> {
        val plans = planRepository.findAllWithUser()
        val whetherForecasts = whetherRepository.getForecastsWithin8Days()
        plans.forEach {
            it.addWhetherInfo(whetherForecasts)
        }
        return plans
    }

    fun save(plan: SavePlanRequest) {
        planRepository.save(
                plan.id,
                plan.userId,
                plan.title,
                plan.description,
                Prefecture.of(plan.prefecture),
                plan.startDate,
                plan.endDate,
                StatusEnum.getStatus(plan.status)
        )
    }

    fun update(plan: UpdatePlanRequest) {
        planRepository.update(
                plan.id,
                plan.title,
                plan.description,
                if (plan.prefecture != null) Prefecture.of(plan.prefecture) else null,
                plan.startDate,
                plan.endDate,
                if (plan.status != null) StatusEnum.getStatus(plan.status) else null
        )
    }

    fun delete(id: Long) {
        planRepository.delete(id)
    }
}