package com.extraaedge.assignment.spacerocket.data.model

import androidx.room.ColumnInfo

data class FirstStage(
    @ColumnInfo(name = "burn_time_sec") val burn_time_sec: Int,
    @ColumnInfo(name = "engines") val engines: Int,
    @ColumnInfo(name = "fuel_amount_tons") val fuel_amount_tons: Double,
    @ColumnInfo(name = "reusable") val reusable: Boolean,
    @ColumnInfo(name = "thrust_sea_level") val thrust_sea_level: Thrust,
    @ColumnInfo(name = "thrust_vacuum") val thrust_vacuum: Thrust
)