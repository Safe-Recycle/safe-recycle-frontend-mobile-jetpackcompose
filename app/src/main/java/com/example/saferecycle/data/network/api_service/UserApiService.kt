package com.example.saferecycle.data.network.api_service

import com.example.saferecycle.data.model.BaseResponse
import com.example.saferecycle.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface UserApiService {
    @GET("auth/users/me")
    suspend fun getUserData(): Response<User>

    @PATCH("users/{id}")
    suspend fun updateUserData(
        @Path("id") userId: Int,
        @Body request: HashMap<String, String>
    ): Response<BaseResponse<User>>
}