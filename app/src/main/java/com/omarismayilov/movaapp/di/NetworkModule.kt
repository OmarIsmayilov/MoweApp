package com.omarismayilov.movaapp.di

import com.google.gson.Gson
import com.omarismayilov.movaapp.common.utils.Constants.BASE_URL
import com.omarismayilov.movaapp.data.network.MovieApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(gsonConverter:GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverter)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit):MovieApiService{
        return retrofit.create(MovieApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideGsonConverter():GsonConverterFactory{
        return GsonConverterFactory.create()
    }

}