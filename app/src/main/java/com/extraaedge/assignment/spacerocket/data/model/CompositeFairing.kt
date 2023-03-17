package com.extraaedge.assignment.spacerocket.data.model

import androidx.room.ColumnInfo

data class CompositeFairing(
    @ColumnInfo(name = "diameter") val diameter: Height,
    @ColumnInfo(name = "height") val height: Height
)