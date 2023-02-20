package com.plan.manager.presentation

import com.plan.manager.presentation.dto.*
import com.plan.manager.usecase.PlanUseCase
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("plan")
@CrossOrigin
class PlanController(
        private val planUseCase: PlanUseCase
) {
    @GetMapping("/list")
    fun getList(): GetPlanListResponse {
        val planList = planUseCase.getList()
        val planListResponse = planList.map {
            PlanElements(
                    it.id,
                    UserElements(
                            it.user.id,
                            it.user.name,
                    ),
                    it.title,
                    it.description,
                    it.prefecture.value.prefecture,
                    it.startDate,
                    it.endDate,
                    it.status.toString(),
                    if(it.weather != null)
                    WhetherElements(
                            it.weather.dt,
                            TemperatureElements(
                                    it.weather.tem
                            ),
                            it.weather.sunrise,
                            it.weather.sunset,
                            it.weather.whether
                    ) else null
            )
        }

        return GetPlanListResponse(planListResponse)
    }
    @PostMapping("/save")
    fun save(@RequestBody request: SavePlanRequest): PlanElements {
        val plan = planUseCase.save(request)
        return PlanElements(
                plan.id,
                UserElements(
                        plan.user.id,
                        plan.user.name,
                ),
                plan.title,
                plan.description,
                plan.prefecture.value.prefecture,
                plan.startDate,
                plan.endDate,
                plan.status.toString(),
                if(plan.weather != null)
                    WhetherElements(
                            plan.weather.dt,
                            TemperatureElements(
                                    plan.weather.tem
                            ),
                            plan.weather.sunrise,
                            plan.weather.sunset,
                            plan.weather.whether
                    ) else null
        )
    }

    @PutMapping("/update")
    fun update(@RequestBody request: UpdatePlanRequest): PlanElements {
        val updatedPlan = planUseCase.update(request)
        return PlanElements(
                updatedPlan.id,
                UserElements(
                        updatedPlan.user.id,
                        updatedPlan.user.name,
                ),
                updatedPlan.title,
                updatedPlan.description,
                updatedPlan.prefecture.value.prefecture,
                updatedPlan.startDate,
                updatedPlan.endDate,
                updatedPlan.status.toString(),
                if(updatedPlan.weather != null)
                    WhetherElements(
                            updatedPlan.weather.dt,
                            TemperatureElements(
                                    updatedPlan.weather.tem
                            ),
                            updatedPlan.weather.sunrise,
                            updatedPlan.weather.sunset,
                            updatedPlan.weather.whether
                ) else null
        )
    }

    @DeleteMapping("/delete/{plan_id}")
    fun delete(@PathVariable("plan_id") id: Long): DeletePlanId {
        return DeletePlanId(planUseCase.delete(id))
    }
}