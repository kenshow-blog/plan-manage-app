package com.plan.manager.domain.type

import com.plan.manager.application.consts.PrefectureEnum

/**
 * 住所クラス
 */
data class Prefecture(
    val value: PrefectureEnum
) {
    companion object {
        /**
         * 引数から都道府県Enum情報を取得する。
         */
        fun of(prefecture: String): Prefecture = PrefectureEnum.values().find { it.prefecture == prefecture }
            ?.let { Prefecture(it) }
            ?:  throw IllegalArgumentException("都道府県ではありません。")
    }
}
