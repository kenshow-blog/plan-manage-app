package com.plan.manager.domain.model

import com.plan.manager.domain.type.Prefecture
import com.plan.manager.domain.type.StatusEnum
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

/**
 * 予定クラス
 */
data class Plan(
        val id: Long,
        val user: User,
        val title: String,
        val description: String,
        val prefecture: Prefecture,
        val startDate: LocalDateTime,
        val endDate: LocalDateTime,
        val status: StatusEnum,
        val whether: Whether
) {

    companion object {
        /**
         * 付加情報(天気など)がない予定クラスを生成する。
         * @param id
         * @param user
         * @param title
         * @param description
         * @param prefecture
         * @param start_date
         * @param end_date
         * @param status
         */
        fun of(id: Long, user: User, title: String, description: String, prefecture: Prefecture, start_date: LocalDateTime, end_date: LocalDateTime, status: StatusEnum): Plan {
            return Plan(
                    id,
                    user,
                    title,
                    description,
                    prefecture,
                    start_date,
                    end_date,
                    status,
                    Whether.createdNotWhetherForecast()
            )
        }
    }
    /**
     * 指定した属性の値を変更したインスタンスを返却
     */
    private fun changeAttributes(
            id: Long = this.id,
            user: User = this.user,
            title: String = this.title,
            description: String = this.description,
            prefecture: Prefecture = this.prefecture,
            start_date: LocalDateTime = this.startDate,
            end_date: LocalDateTime = this.endDate,
            status: StatusEnum = this.status,
            whether: Whether = this.whether
    ) : Plan {
        return Plan(
                id, user, title, description, prefecture, start_date, end_date, status, whether
        )
    }
    /**
     * 天気情報を付与する
     * @param whetherList
     */
    fun addWhetherInfo(whetherList: List<Whether>) {
        whetherList.forEach{
            val sdf = SimpleDateFormat("yyyy/MM/dd")

            val planStartDate = sdf.format(this.startDate)
            val whetherDate = sdf.format(it.dt)
            if(planStartDate == whetherDate) changeAttributes(whether = it)
        }
    }
    /**
     * 実施日が8日以内であるかを確認する。
     */
    fun isStartDateWithin8Days(): Boolean {
        val now = LocalDateTime.now()
        val days = ChronoUnit.DAYS.between(now, this.startDate)
        return days < 8
    }
}
