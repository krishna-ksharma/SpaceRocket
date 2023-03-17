package com.extraaedge.assignment.spacerocket.data.model

import androidx.room.ColumnInfo

data class SecondStage(
    @ColumnInfo(name = "burn_time_sec") val burn_time_sec: Int,
    @ColumnInfo(name = "engines") val engines: Int,
    @ColumnInfo(name = "fuel_amount_tons") val fuel_amount_tons: Double,
    @ColumnInfo(name = "payloads") val payloads: Payloads,
    @ColumnInfo(name = "reusable") val reusable: Boolean,
    @ColumnInfo(name = "thrust") val thrust: Thrust
)