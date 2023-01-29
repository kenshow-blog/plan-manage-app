package com.plan.manager.infrastructure.repository

import com.plan.manager.application.client.CommonHttpClient
import com.plan.manager.domain.model.Plan
import com.plan.manager.domain.model.Whether
import com.plan.manager.domain.repository.WhetherRepository
import com.plan.manager.domain.type.Prefecture
import com.plan.manager.domain.type.Temperature
import com.plan.manager.infrastructure.dto.OpenWhetherDailyResponse
import org.apache.http.message.BasicNameValuePair
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.Date

/**
 * 天気ポジトリ実装クラス
 */
@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class DefaultWhetherRepository(
        private val commonHttpClient: CommonHttpClient
): WhetherRepository {
    @Value("\${openwhether.api.uri}")
    val uri = ""
    @Value("\${openwhether.api.key}")
    val key = ""
    override fun getForecastsWithin8Days(prefecture: Prefecture): List<Whether> {
        val response = commonHttpClient.get(
                uri = this.uri,
                headers = mapOf("Content-type" to "application/json"),
                queryParameters = listOf(
                        BasicNameValuePair("lat", prefecture.value.lat.toString()),
                        BasicNameValuePair("lon", prefecture.value.lon.toString()),
                        BasicNameValuePair("exclude", "hourly,minutely,current"),
                        BasicNameValuePair("appid", this.key),
                ),
                OpenWhetherDailyResponse::class.java
        )
        return response.daily.map {
            Whether(
                    Date(it.dt),
                    Temperature(
                            it.temp.day,
                            it.temp.min,
                            it.temp.max,
                            it.temp.night,
                            it.temp.eve,
                            it.temp.morn,
                    ),
                    Date(it.sunrise),
                    Date(it.sunset),
                    it.whether[0].description
            )
        }
    }
}