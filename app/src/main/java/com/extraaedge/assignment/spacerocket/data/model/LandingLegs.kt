package com.extraaedge.assignment.spacerocket.data.model

import androidx.room.ColumnInfo

data class LandingLegs(
    @ColumnInfo(name = "material") val material: String,
    @ColumnInfo(name = "number") val number: Int
)