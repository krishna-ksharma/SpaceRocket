package com.extraaedge.assignment.spacerocket.data.model

import androidx.room.ColumnInfo

data class Engines(
    @ColumnInfo(name = "engine_loss_max") val engine_loss_max: Int,
    @ColumnInfo(name = "isp") val isp: Isp,
    @ColumnInfo(name = "layout") val layout: String,
    @ColumnInfo(name = "number") val number: Int,
    @ColumnInfo(name = "propellant_1") val propellant_1: String,
    @ColumnInfo(name = "propellant_2") val propellant_2: String,
    @ColumnInfo(name = "thrust_sea_level") val thrust_sea_level: Thrust,
    @ColumnInfo(name = "thrust_to_weight") val thrust_to_weight: Double,
    @ColumnInfo(name = "thrust_vacuum") val thrust_vacuum: Thrust,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "version") val version: String
)