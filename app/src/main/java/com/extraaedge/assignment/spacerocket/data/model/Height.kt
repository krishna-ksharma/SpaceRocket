package com.extraaedge.assignment.spacerocket.data.model

import androidx.room.ColumnInfo

data class Height(
    @ColumnInfo(name = "feet") val feet: Double,
    @ColumnInfo(name = "meters") val meters: Double
) {
    fun feetInches(): String {
        val measurement = feet
        val feet = measurement.toInt()
        val inches = (12.0 * (measurement - feet)).toInt()
        return "$feet\' ${inches}\""
    }
}