package com.extraaedge.assignment.spacerocket.di

import android.content.Context
import androidx.room.Room
import com.extraaedge.assignment.spacerocket.data.local.RocketDao
import com.extraaedge.assignment.spacerocket.data.local.RocketDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RocketDatabaseModule {
    @Singleton
    @Provides
    fun provideRocketDatabase(@ApplicationContext context: Context): RocketDatabase {
        return Room.databaseBuilder(
            context,
            RocketDatabase::class.java,
            "rocket_db.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideRocketDao(rocketDatabase: RocketDatabase): RocketDao {
        return rocketDatabase.rocketDAo()
    }
}