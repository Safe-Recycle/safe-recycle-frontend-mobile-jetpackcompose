package com.example.saferecycle.data.network.api_service

import com.example.saferecycle.data.model.BaseResponse
import com.example.saferecycle.data.model.Category
import com.example.saferecycle.data.model.Waste
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WasteApiService {
    @GET("items/{wasteId}")
    suspend fun getWasteDetails(
        @Path("wasteId") userId: Int,
    ): Response<BaseResponse<Waste>>
}