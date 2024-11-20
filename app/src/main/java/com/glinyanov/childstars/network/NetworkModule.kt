package com.glinyanov.childstars.network

import com.google.gson.GsonBuilder
import dagger.hilt.InstallIn
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.glinyanov.childstars.api.ChildStarsService
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideChildStarsService(): ChildStarsService {
        return Retrofit.Builder()
            .client(OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().setLevel(
                        HttpLoggingInterceptor.Level.BASIC))
                .addInterceptor(
                    HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY))
                .build())

            .baseUrl("http://192.168.88.253:8080/")
            .addConverterFactory(GsonConverterFactory.create(
                GsonBuilder()
                .registerTypeAdapter(Date::class.java, DateTypeAdapter())
                .create()))
            .build()
            .create(ChildStarsService::class.java)
    }
}