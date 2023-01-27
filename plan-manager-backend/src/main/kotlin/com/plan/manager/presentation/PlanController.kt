package com.plan.manager.presentation

import com.plan.manager.presentation.dto.*
import com.plan.manager.usecase.PlanUseCase
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
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
                    it.address.prefecture.name,
                    it.start_date,
                    it.end_date,
                    it.status.toString(),
                    WhetherElements(
                            it.whether.dt,
                            TemperatureElements(
                                    it.whether.tem
                            ),
                            it.whether.sunrise,
                            it.whether.sunset,
                            it.whether.whether
                    )
            )
        }

        return GetPlanListResponse(planListResponse)
    }
}