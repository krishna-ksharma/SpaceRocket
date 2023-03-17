package com.extraaedge.assignment.spacerocket.data.model

import androidx.room.ColumnInfo

data class ThrustSeaLevel(
    @ColumnInfo(name = "kN") val kN: Int,
    @ColumnInfo(name = "lbf") val lbf: Int
)