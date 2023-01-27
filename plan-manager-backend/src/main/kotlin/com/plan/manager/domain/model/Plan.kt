package com.plan.manager.domain.model

import com.plan.manager.domain.type.Address
import com.plan.manager.domain.type.StatusEnum
import java.text.SimpleDateFormat

/**
 * 予定クラス
 */
data class Plan(
    val id: Long,
    val user: User,
    val title: String,
    val description: String,
    val address: Address,
    val start_date: String,
    val end_date: String,
    val status: StatusEnum,
    var whether: Whether
) {
    companion object {
        /**
         * 付加情報(天気など)がない予定クラスを生成する。
         * @param id
         * @param user
         * @param title
         * @param description
         * @param address
         * @param start_date
         * @param end_date
         * @param status
         */
        fun of(id: Long, user: User, title: String, description: String, address: Address, start_date: String, end_date: String,status: StatusEnum): Plan {
            return Plan(
                    id,
                    user,
                    title,
                    description,
                    address,
                    start_date,
                    end_date,
                    status,
                    Whether.createdNotWhetherForecast()
            )
        }
    }
    /**
     * 天気情報を付与する
     * @param whetherList
     */
    fun addWhetherInfo(whetherList: List<Whether>) {
        whetherList.forEach{
            val sdf = SimpleDateFormat("yyyy/MM/dd")
            val planStartDate = sdf.format(this.start_date)
            val whetherDate = sdf.format(it.dt)
            if(planStartDate == whetherDate) this.whether = it
        }
    }
}
