package com.example.saferecycle.data.repository

import com.example.saferecycle.data.model.ErrorResponse
import com.example.saferecycle.ui.state.AppError
import com.example.saferecycle.data.network.DataResult
import com.google.gson.Gson
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>
    ): DataResult<T> {
        return try {
            val response = apiCall()

            if (response.isSuccessful) {
                val body = response.body()
                if (body == null) {
                    DataResult.Empty
                } else {
                    DataResult.Success(body)
                }
            } else {
                val errorBody = response.errorBody()
                val errorResponse = errorBody?.let {
                    runCatching {
                        Gson().fromJson(it.charStream(), ErrorResponse::class.java)
                    }.getOrNull()
                }

                DataResult.Error(
                    mapHttpError(
                        code = response.code(),
                        message = errorResponse?.message
                    )
                )
            }
        } catch (e: IOException) {
            DataResult.Error(AppError.Network("No Internet Connection"))
        } catch (e: Exception) {
            DataResult.Error(AppError.Unknown(e.message?:"Unknown Error"))
        }
    }
    private fun mapHttpError(
        code: Int,
        message: String?
    ): AppError =
        when (code) {
            401 -> AppError.Unauthorized(message?:"Unauthorized Error")
            403 -> AppError.Forbidden(message?:"Forbidden Error")
            404 -> AppError.NotFound(message?:"NotFound Error")
            in 500..599 -> AppError.Server(message?:"Server Error")
            else -> AppError.Unknown(message?:"Unknown Error")
        }
}