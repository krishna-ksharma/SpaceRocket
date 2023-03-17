package com.extraaedge.assignment.spacerocket.data.model

import androidx.room.ColumnInfo

data class Payloads(
    @ColumnInfo(name = "composite_fairing") val composite_fairing: CompositeFairing,
    @ColumnInfo(name = "option_1") val option_1: String
)