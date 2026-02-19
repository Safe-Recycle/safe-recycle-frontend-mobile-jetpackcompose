package com.example.saferecycle.data.repository

import com.example.saferecycle.data.model.BaseResponse
import com.example.saferecycle.data.model.ScanWasteResponse
import com.example.saferecycle.data.model.Waste
import com.example.saferecycle.data.network.DataResult
import com.example.saferecycle.data.network.api_service.ScanApiService
import com.example.saferecycle.ui.state.AppError
import com.google.gson.Gson
import okhttp3.MultipartBody
import javax.inject.Inject
import retrofit2.converter.gson.GsonConverterFactory

class ScanRepository @Inject constructor(
    private val api: ScanApiService
) : BaseRepository() {
    suspend fun scanWaste(file: MultipartBody.Part): DataResult<ScanWasteResponse> {
        val checkWasteResult = safeApiCall { api.checkWaste(file) }
        return when (checkWasteResult) {
            is DataResult.Success -> {
                val baseResponse = checkWasteResult.data
                val data = baseResponse.data
                return if (data.id != null) {
                    DataResult.Success(data)
                } else {
                    processWaste(file)
                }
            }
            is DataResult.Error -> {
                DataResult.Error(error = checkWasteResult.error)
            }

            else -> DataResult.Error(AppError.Unknown("Unknown Error"))
        }
    }

    suspend fun processWaste(file: MultipartBody.Part): DataResult<ScanWasteResponse> {
        val result = safeApiCall { api.processWaste(file) }
        return when (result) {
            is DataResult.Success -> {
                val baseResponse = result.data
                val data = baseResponse.data
                if (data.id != null) {
                    DataResult.Success(data)
                } else {
                    DataResult.Error(
                        AppError.NotFound(
                            message = data.message
                                ?: "Item could not be identified as trash"
                        )
                    )
                }
            }
            is DataResult.Error -> {
                DataResult.Error(result.error)
            }
            else -> {
                DataResult.Error(AppError.Unknown("Unknown Error"))
            }
        }
    }
}