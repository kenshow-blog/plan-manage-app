package com.plan.manager.domain.model

import com.plan.manager.domain.type.Prefecture
import com.plan.manager.domain.type.StatusEnum
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
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
        val startDate: LocalDateTime,
        val endDate: LocalDateTime,
        val status: StatusEnum,
        val weather: Weather
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
                    Weather.createdNotWhetherForecast()
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
            weather: Weather = this.weather
    ) : Plan {
        return Plan(
                id, user, title, description, prefecture, start_date, end_date, status, weather
        )
    }
    /**
     * 天気情報を付与する
     * @param weatherList
     */
    fun addWhetherInfo(weatherList: List<Weather>): Plan {
        weatherList.forEach{
            val localDateTimeOfForecast = toLocalDateTime(it.dt)
            val localDateOfForecast = LocalDate.of(localDateTimeOfForecast.year, localDateTimeOfForecast.month, localDateTimeOfForecast.dayOfMonth)
            val localDateOfStartDate = LocalDate.of(this.startDate.year, this.startDate.month, this.startDate.dayOfMonth)
            if(localDateOfForecast == localDateOfStartDate) return changeAttributes(weather = it)
        }
        return this
    }
    /**
     * 実施日が8日以内であるかを確認する。
     */
    fun isStartDateWithin8Days(): Boolean {
        val now = LocalDateTime.now()
        val days = ChronoUnit.DAYS.between(now, this.startDate)
        return days < 8
    }
    private fun toLocalDateTime(date: Date): LocalDateTime {
        val instant = date.toInstant()
        return LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Tokyo"))
    }
}
