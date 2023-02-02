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
                    it.prefecture.value.name,
                    it.startDate,
                    it.endDate,
                    it.status.toString(),
                    WhetherElements(
                            it.weather.dt,
                            TemperatureElements(
                                    it.weather.tem
                            ),
                            it.weather.sunrise,
                            it.weather.sunset,
                            it.weather.whether
                    )
            )
        }

        return GetPlanListResponse(planListResponse)
    }
    @PostMapping("/save")
    fun save(@RequestBody request: SavePlanRequest) {
        planUseCase.save(request)
    }

    @PutMapping("/update")
    fun update(@RequestBody request: UpdatePlanRequest) {
        planUseCase.update(request)
    }

    @DeleteMapping("/delete/{plan_id}")
    fun delete(@PathVariable("plan_id") id: Long) {
        planUseCase.delete(id)
    }
}