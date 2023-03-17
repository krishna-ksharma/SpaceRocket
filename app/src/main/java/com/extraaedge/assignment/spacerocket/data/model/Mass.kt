package com.extraaedge.assignment.spacerocket.data.model

import androidx.room.ColumnInfo

data class Mass(
    @ColumnInfo(name = "kg") val kg: Int,
    @ColumnInfo(name = "lb") val lb: Int
)