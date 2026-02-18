package com.example.saferecycle.data.network.api_service

import com.example.saferecycle.data.model.Category
import retrofit2.Response
import retrofit2.http.GET

interface CategoryApiService {
    @GET("categories")
    suspend fun getCategories(): Response<List<Category>>
}