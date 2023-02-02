package com.plan.manager.usecase

import com.plan.manager.domain.model.Plan
import com.plan.manager.domain.model.Weather
import com.plan.manager.domain.repository.PlanRepository
import com.plan.manager.domain.repository.UserRepository
import com.plan.manager.domain.repository.WhetherRepository
import com.plan.manager.domain.type.Prefecture
import com.plan.manager.domain.type.StatusEnum
import com.plan.manager.presentation.dto.SavePlanRequest
import com.plan.manager.presentation.dto.UpdatePlanRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 予定ユースケース
 */
@Service
class PlanUseCase(
        private val planRepository: PlanRepository,
        private val whetherRepository: WhetherRepository,
        private val userRepository: UserRepository
) {
    @Transactional
    fun getList(): List<Plan> {
        val plans = planRepository.findAllWithUser()
        val weatherOfPrefecture = mutableMapOf<Prefecture, List<Weather>>()
        val plansWithWeatherForecast = plans.map {
            if (it.isStartDateWithin8Days() && !weatherOfPrefecture.containsKey(it.prefecture)) {
                val whetherForecasts = whetherRepository.getForecastsWithin8Days(it.prefecture)
                weatherOfPrefecture[it.prefecture] = whetherForecasts
            }
            weatherOfPrefecture[it.prefecture]?.let { it1 ->  it.addWhetherInfo(it1) } ?: run { it }
        }
        return plansWithWeatherForecast
    }

    fun save(request: SavePlanRequest) {
        val user = userRepository.findOne(request.userId) ?: throw IllegalArgumentException("与えられたuserIdに紐づくユーザーは存在しません。")
        val plan = Plan.create(
                user,
                request.title,
                request.description,
                Prefecture.of(request.prefecture),
                request.startDate,
                request.endDate,
                StatusEnum.getStatus(request.status)
        )
        planRepository.save(plan)
    }

    fun update(request: UpdatePlanRequest) {
        val plan = planRepository.findOne(request.id) ?: throw IllegalArgumentException("与えられたplanIdに紐づくユーザーは存在しません。")
        val user = userRepository.findOne(request.userId) ?: throw IllegalArgumentException("与えられたuserIdに紐づくユーザーは存在しません。")
        if (user.id != plan.user.id) throw IllegalArgumentException("他人のユーザーの予定を編集することはできません。")

        val prefecture = if (request.prefecture != null) Prefecture.of(request.prefecture) else null
        val status = if (request.status != null) StatusEnum.getStatus(request.status) else null
        planRepository.update(
                request.id,
                request.title,
                request.description,
                prefecture,
                request.startDate,
                request.endDate,
                status
        )
    }

    fun delete(id: Long) {
        val plan = planRepository.findOne(id) ?: throw IllegalArgumentException("与えられたplanIdに紐づくユーザーは存在しません。")
        planRepository.delete(plan.id)
    }
}