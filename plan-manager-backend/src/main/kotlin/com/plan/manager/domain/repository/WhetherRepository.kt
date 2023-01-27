package com.plan.manager.domain.repository

import com.plan.manager.domain.model.Whether

/**
 * 天気リポジトリインターフェース
 */
interface WhetherRepository {
    /**
     * 実行日から8日以内の天気予報を取得する
     */
    fun getForecastsWithin8Days(): List<Whether>
}