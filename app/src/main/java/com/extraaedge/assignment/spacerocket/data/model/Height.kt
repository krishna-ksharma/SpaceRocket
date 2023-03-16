package com.extraaedge.assignment.spacerocket.data.model

data class Height(
    val feet: Double,
    val meters: Double
) {
    fun feetInches(): String {
        val measurement = feet
        val feet = measurement.toInt()
        val inches = (12.0 * (measurement - feet)).toInt()
        return "$feet\' ${inches}\""
    }
}