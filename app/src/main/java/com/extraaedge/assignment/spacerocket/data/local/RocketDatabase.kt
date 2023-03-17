package com.extraaedge.assignment.spacerocket.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.extraaedge.assignment.spacerocket.data.local.Converter
import com.extraaedge.assignment.spacerocket.data.local.RocketDao
import com.extraaedge.assignment.spacerocket.data.model.Rocket

@Database(
    version = 1,
    entities = [Rocket::class],
)
@TypeConverters(Converter::class)
abstract class RocketDatabase : RoomDatabase() {

    abstract fun rocketDAo(): RocketDao
}