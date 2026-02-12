package com.example.saferecycle.di

import com.example.saferecycle.data.network.SessionManager
import com.example.saferecycle.data.network.TokenManager
import com.example.saferecycle.data.network.api_service.AuthApiService
import com.example.saferecycle.data.network.interceptor.AuthInterceptor
import com.example.saferecycle.data.network.interceptor.TokenAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton





@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://192.168.137.1:8000/api/"

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    // ================= REFRESH TOKEN & LOGIN CLIENT  =================

    @Provides
    @Singleton
    @RefreshClient
    fun provideRefreshOkHttp(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    @RefreshClient
    fun provideRefreshRetrofit(
        @RefreshClient okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    @RefreshClient
    fun provideRefreshAuthApi(
        @RefreshClient retrofit: Retrofit
    ): AuthApiService =
        retrofit.create(AuthApiService::class.java)

    // ================= MAIN CLIENT =================

    @Provides
    @Singleton
    fun provideTokenAuthenticator(
        tokenManager: TokenManager,
        @RefreshClient authApi: AuthApiService, // ✅ sudah aman
        sessionManager: SessionManager
    ): TokenAuthenticator =
        TokenAuthenticator(tokenManager, authApi, sessionManager)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        tokenAuthenticator: TokenAuthenticator,
        loggingInterceptor: HttpLoggingInterceptor,
        tokenManager: TokenManager
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenManager))
            .authenticator(tokenAuthenticator)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // ================= API SERVICE =================


    @Provides
    @Singleton
    fun provideAuthApi(
        @RefreshClient retrofit: Retrofit
    ): AuthApiService =
        retrofit.create(AuthApiService::class.java)
}

