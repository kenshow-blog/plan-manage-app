package com.plan.manager.infrastructure.repository

import com.plan.manager.domain.model.Whether
import com.plan.manager.domain.repository.WhetherRepository
import com.plan.manager.domain.type.Temperature
import org.springframework.stereotype.Repository
import java.util.Date

/**
 * 天気ポジトリ実装クラス
 */
@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class DefaultWhetherRepository: WhetherRepository {
    override fun getForecastsWithin8Days(): List<Whether> {
        return listOf(
                Whether(
                     Date(1674698400),
                     Temperature(
                                0.0,
                                0.0,
                                0.0,
                                0.0,
                                0.0,
                                0.0,
                     ),
                     Date(),
                     Date(),
                        "晴天"
                )
        )
    }
}