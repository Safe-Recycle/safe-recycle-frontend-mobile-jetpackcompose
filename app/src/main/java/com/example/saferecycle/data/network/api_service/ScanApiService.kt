package com.example.saferecycle.data.network.api_service

import com.example.saferecycle.data.model.BaseResponse
import com.example.saferecycle.data.model.ScanWasteResponse
import com.example.saferecycle.data.model.Waste
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ScanApiService {
    @Multipart
    @POST("llm/check")
    suspend fun checkWaste(
        @Part file : MultipartBody.Part
    ): Response<BaseResponse<ScanWasteResponse>>

    @Multipart
    @POST("llm/process")
    suspend fun processWaste(
        @Part file : MultipartBody.Part
    ): Response<BaseResponse<ScanWasteResponse>>
}