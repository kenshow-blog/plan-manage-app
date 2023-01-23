package com.plan.manager.domain.type

/**
 * 気温クラス
 */
data class Temperature(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double,
)
