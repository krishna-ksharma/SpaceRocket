package com.extraaedge.assignment.spacerocket.data.local

import androidx.room.*
import com.extraaedge.assignment.spacerocket.data.model.Rocket

@Dao
interface RocketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rocket: Rocket): Long

    @Query("SELECT * FROM ROCKET")
    fun getAllRockets(): List<Rocket>

    @Query("DELETE FROM ROCKET")
    suspend fun deleteAllRockets()
}