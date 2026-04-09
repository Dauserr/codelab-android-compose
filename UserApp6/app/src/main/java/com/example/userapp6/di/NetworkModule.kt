package com.example.userapp6.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.userapp6.data.remote.UserApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideChuckerInterceptor(): ChuckerInterceptor =
        ChuckerInterceptor.Builder(context)
            .alwaysReadResponseBody(true)
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(chuckerInterceptor: ChuckerInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideUserApiService(retrofit: Retrofit): UserApiService =
        retrofit.create(UserApiService::class.java)
}