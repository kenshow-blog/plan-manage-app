package com.plan.manager.domain.type

/**
 * 気温クラス
 */
data class Temperature(
    val day: String,
    val min: String,
    val max: String,
    val night: String,
    val eve: String,
    val morn: String,
) {
    companion object {
        fun toDegree(day: Double, min: Double, max: Double, night: Double, eve: Double, morn: Double): Temperature {
            val kelvin = -273.15
            return Temperature(
                String.format("%.1f", day + kelvin),
                String.format("%.1f", min + kelvin),
                String.format("%.1f", max + kelvin),
                String.format("%.1f", night + kelvin),
                String.format("%.1f", eve + kelvin),
                String.format("%.1f", morn + kelvin),
            )
        }
    }
}
