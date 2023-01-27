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
                "COMPLETE" -> COMPLETE
                "PROCESS" -> PROCESS
                "UNPROCESSED" -> UNPROCESSED
                else -> throw IllegalArgumentException("ステータスの値が不正です。")
            }
        }
    }
}