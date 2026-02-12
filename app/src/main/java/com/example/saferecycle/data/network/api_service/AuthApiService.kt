package com.example.saferecycle.data.network.api_service

import com.example.saferecycle.data.model.AuthResponse
import com.example.saferecycle.data.model.LogoutMessage
import com.example.saferecycle.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

    interface AuthApiService {
        @FormUrlEncoded
        @POST("auth/token")
        @Headers("Content-Type:application/x-www-form-urlencoded")
        suspend fun login(
            @Field("username") email: String,
            @Field("password") password: String
        ): Response<AuthResponse>

        @POST("auth/logout")
        suspend fun logout(
            @Header("Authorization") token: String,
            @Body request: HashMap<String, String>
        ): Response<LogoutMessage>

        @POST("auth/refresh")
        suspend fun refreshToken(
            @Body request: HashMap<String, String>
        ): Response<AuthResponse>

        @POST("auth/register")
        suspend fun register(
            @Body user: User
        ):Response<User>
    }