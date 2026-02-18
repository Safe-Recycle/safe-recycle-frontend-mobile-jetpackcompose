package com.example.saferecycle.di

import android.content.Context
import com.example.saferecycle.data.network.SessionManager
import com.example.saferecycle.data.network.TokenManager
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
//    @Provides
//    @Singleton
//    fun provideAuthRepository(
//        @ApplicationContext
//        context: Context,
//        tokenManager: TokenManager,
//        sessionManager: SessionManager,
//
//        ): AuthRepository {
//        return AuthRepository(
//            context,
//            api = ,
//            tokenManager = tokenManager,
//            sessionManager = sessionManager
//        )
//    }

//    @Provides
//    @Singleton
//    fun provideCategoryRepository(
//        @ApplicationContext
//        context: Context
//    ): CategoryRepository {
//        return CategoryRepository(context)
//    }

    @Provides
    @Singleton
    fun provideWasteRepository(
        @ApplicationContext
        context: Context
    ): WasteRepository {
        return WasteRepository(context)
    }
}