package com.example.saferecycle.di

import com.example.saferecycle.data.network.SessionManager
import com.example.saferecycle.data.network.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SessionModule {

    @Provides
    @Singleton
    fun provideSessionManager(
        tokenManager: TokenManager
    ): SessionManager = SessionManager(tokenManager)
}