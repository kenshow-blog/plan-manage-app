package com.plan.manager.domain.repository

import com.plan.manager.domain.model.Plan

/**
 * プランリポジトリインターフェース
 */
interface PlanRepository {
    /**
     * 予定リストを天気予報情報と一緒に生成する。
     */
    fun findAllWithUser(): List<Plan>
}