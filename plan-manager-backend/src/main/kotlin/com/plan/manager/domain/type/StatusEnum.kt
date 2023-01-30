package com.plan.manager.domain.type

/**
 * ステータスEnumクラス
 */
enum class StatusEnum {
    COMPLETE,
    PROCESS,
    UNPROCESSED;
    companion object {
        fun getStatus(status: String): StatusEnum {
            return when (status) {
                "完了" -> COMPLETE
                "処理中" -> PROCESS
                "未処理" -> UNPROCESSED
                else -> throw IllegalArgumentException("ステータスの値が不正です。")
            }
        }
    }
}