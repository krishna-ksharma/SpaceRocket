package com.extraaedge.assignment.spacerocket.data.model

import androidx.room.ColumnInfo

data class Isp(
    @ColumnInfo(name = "sea_level") val sea_level: Int,
    @ColumnInfo(name = "vacuum") val vacuum: Int
)