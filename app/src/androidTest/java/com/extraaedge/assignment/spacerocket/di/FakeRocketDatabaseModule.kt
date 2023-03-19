package com.extraaedge.assignment.spacerocket.di

import android.content.Context
import androidx.room.Room
import com.extraaedge.assignment.spacerocket.data.local.RocketDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
//@TestInstallIn(components = [SingletonComponent::class], replaces = [RocketDatabaseModule::class])
class FakeRocketDatabaseModule {

    @Singleton
    @Provides
    @Named("fakeDb")
    fun provideFakeRocketDatabase(@ApplicationContext context: Context): RocketDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            RocketDatabase::class.java
        ).allowMainThreadQueries().build()
    }
}