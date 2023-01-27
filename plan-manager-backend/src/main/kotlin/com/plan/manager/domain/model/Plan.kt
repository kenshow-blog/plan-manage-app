package com.plan.manager.domain.model

import com.plan.manager.domain.type.Prefecture
import com.plan.manager.domain.type.StatusEnum
import java.text.SimpleDateFormat
import java.util.*

/**
 * 予定クラス
 */
data class Plan(
        val id: Long,
        val user: User,
        val title: String,
        val description: String,
        val prefecture: Prefecture,
        val start_date: Date,
        val end_date: Date,
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
        fun of(id: Long, user: User, title: String, description: String, prefecture: Prefecture, start_date: Date, end_date: Date, status: StatusEnum): Plan {
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
            start_date: Date = this.start_date,
            end_date: Date = this.end_date,
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

            val planStartDate = sdf.format(this.start_date)
            val whetherDate = sdf.format(it.dt)
            if(planStartDate == whetherDate) changeAttributes(whether = it)
        }
    }
}
