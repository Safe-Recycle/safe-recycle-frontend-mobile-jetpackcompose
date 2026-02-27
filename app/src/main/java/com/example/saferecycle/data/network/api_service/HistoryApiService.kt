package com.example.saferecycle.data.network.api_service

import com.example.saferecycle.data.model.BaseResponse
import com.example.saferecycle.data.model.Waste
import com.example.saferecycle.data.model.WasteThumbnail
import com.example.saferecycle.data.model.WasteThumbnailPopular
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HistoryApiService {
    @GET("history/popular")
    suspend fun getPopularWaste(): Response<BaseResponse<List<WasteThumbnailPopular>>>

    @GET("history/recommendation/{userId}")
    suspend fun getSuggestedWaste(
        @Path("userId") userId: Int,
    ): Response<BaseResponse<List<WasteThumbnail>>>
}