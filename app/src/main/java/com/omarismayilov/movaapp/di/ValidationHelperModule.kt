package com.omarismayilov.movaapp.di

import android.content.Context
import com.omarismayilov.movaapp.common.utils.ValidationHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ValidationHelperModule {

    @Singleton
    @Provides
    fun provideValidationHelper(@ApplicationContext context: Context) = ValidationHelper(context)
}