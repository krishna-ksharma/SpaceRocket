package com.extraaedge.assignment.spacerocket.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Rocket")
data class Rocket(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "active") val active: Boolean,
    @ColumnInfo(name = "boosters") val boosters: Int,
    @ColumnInfo(name = "company") val company: String,
    @ColumnInfo(name = "cost_per_launch") val cost_per_launch: Int,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "diameter") val diameter: Diameter,
    @ColumnInfo(name = "engines") val engines: Engines,
    @ColumnInfo(name = "first_flight") val first_flight: String,
    @ColumnInfo(name = "first_stage") val first_stage: FirstStage,
    @ColumnInfo(name = "flickr_images") val flickr_images: List<String>,
    @ColumnInfo(name = "height") val height: Height,
    @ColumnInfo(name = "landing_legs") val landing_legs: LandingLegs,
    @ColumnInfo(name = "mass") val mass: Mass,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "payload_weights") val payload_weights: List<PayloadWeight>,
    @ColumnInfo(name = "second_stage") val second_stage: SecondStage,
    @ColumnInfo(name = "stages") val stages: Int,
    @ColumnInfo(name = "success_rate_pct") val success_rate_pct: Int,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "wikipedia") val wikipedia: String
)