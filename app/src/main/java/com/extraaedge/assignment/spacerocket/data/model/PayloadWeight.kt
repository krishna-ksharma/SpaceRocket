package com.extraaedge.assignment.spacerocket.data.model

import androidx.room.ColumnInfo

data class PayloadWeight(
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "kg") val kg: Int,
    @ColumnInfo(name = "lb") val lb: Int,
    @ColumnInfo(name = "name") val name: String
)