package com.extraaedge.assignment.spacerocket.di

import android.app.Application
import android.content.Context
import com.extraaedge.assignment.spacerocket.data.remote.ApiConstants
import com.extraaedge.assignment.spacerocket.data.remote.SpaceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RocketApiModule {
    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder, okHttpClient: OkHttpClient): SpaceApi {
        return builder.client(okHttpClient).build().create(SpaceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }
}