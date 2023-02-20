package com.plan.manager.domain.repository

import com.plan.manager.domain.model.Plan
import com.plan.manager.domain.type.Prefecture
import com.plan.manager.domain.type.StatusEnum
import java.time.LocalDateTime

/**
 * プランリポジトリインターフェース
 */
interface PlanRepository {
    /**
     * 全予定リストを取得する。
     */
    fun findAllWithUser(): List<Plan>

    /**
     * 予定を作成する。
     */
    fun save(plan: Plan): Long

    /**
     * 予定を更新する。
     */
    fun update(
            id: Long,
            title: String?,
            description: String?,
            prefecture: Prefecture?,
            startDate: LocalDateTime?,
            endDate: LocalDateTime?,
            status: StatusEnum?
    )

    /**
     * 予定を削除する。
     */
    fun delete(id: Long)

    /**
     * idに紐づくプランを取得する。
     */
    fun findOne(id: Long): Plan?
}