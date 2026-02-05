package com.example.saferecycle.di

import android.content.Context
import com.example.saferecycle.data.repository.AuthRepository
import com.example.saferecycle.data.repository.CategoryRepository
import com.example.saferecycle.data.repository.WasteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        @ApplicationContext
        context: Context
    ): AuthRepository {
        return AuthRepository(context)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(
        @ApplicationContext
        context: Context
    ): CategoryRepository {
        return CategoryRepository(context)
    }

    @Provides
    @Singleton
    fun provideWasteRepository(
        @ApplicationContext
        context: Context
    ): WasteRepository {
        return WasteRepository(context)
    }
}