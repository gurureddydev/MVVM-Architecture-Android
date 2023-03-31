package com.gurureddy.mvvm_architecture_android.di.module

import android.content.Context
import com.gurureddy.mvvm_architecture_android.MVVMApplication
import com.gurureddy.mvvm_architecture_android.data.api.NetworkService
import com.gurureddy.mvvm_architecture_android.di.ApplicationContext
import com.gurureddy.mvvm_architecture_android.di.BaseUrl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MVVMApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context {
        return application
    }

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://newsapi.org/v2/"

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }
}
