package com.extraaedge.assignment.spacerocket.di

import com.extraaedge.assignment.spacerocket.com.extraaedge.assignment.spacerocket.data.remote.MockServer
import com.extraaedge.assignment.spacerocket.data.remote.RocketApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FakeRocketApiModule {
    @Provides
    @Named("FakeRocketApi")
    @Singleton
    fun provideTestApi(): RocketApi {
        return Retrofit.Builder().baseUrl(MockServer.server.url("/"))
            .addConverterFactory(GsonConverterFactory.create()).build().create(RocketApi::class.java)
    }
}