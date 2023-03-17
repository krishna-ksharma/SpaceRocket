package com.extraaedge.assignment.spacerocket.data.model

import androidx.room.ColumnInfo

data class CompositeFairing(
    @ColumnInfo(name = "diameter") val diameter: Diameter,
    @ColumnInfo(name = "height") val height: Height
)