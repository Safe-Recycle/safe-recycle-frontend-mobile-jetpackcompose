package com.example.saferecycle.data.network.api_service

import com.example.saferecycle.data.model.BaseResponse
import com.example.saferecycle.data.model.Category
import com.example.saferecycle.data.model.ShowItemResponse
import com.example.saferecycle.data.model.Waste
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WasteApiService {
    @GET("api/items/{wasteId}")
    suspend fun getWasteDetails(
        @Path("wasteId") userId: Int,
    ): Response<BaseResponse<Waste>>

    @GET("api/items")
    suspend fun getWasteList(
        @Query("name") name: String?,
        @Query("category") category: Int?,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<ShowItemResponse>
}