package com.extraaedge.assignment.spacerocket.di

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
class FakeRocketApiModule {
    @Provides
    @Singleton
    fun provideTestApi(builder: Retrofit.Builder, okHttpClient: OkHttpClient): SpaceApi {
        return builder.client(okHttpClient).build().create(SpaceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTestOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideTestRetrofit(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl("http://localhost/")
            .addConverterFactory(GsonConverterFactory.create())
    }
}