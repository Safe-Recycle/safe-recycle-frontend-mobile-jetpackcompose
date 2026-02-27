package com.example.saferecycle.data.model

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("access_token") val accessToken:String,
    @SerializedName("refresh_token") val refreshToken:String,
    @SerializedName("token_type") val tokenType:String
)

data class ErrorResponse(
    val status: String,
    val message: String
)

data class LogoutMessage(
    val message:String
)

data class BaseResponse<T>(
    val status: String,
    val message: String,
    val data: T
)


data class ScanWasteResponse(
    val status:String?,
    val message: String?,
    val name:String?,
    val id:Int?
)

data class ShowItemResponse(
    val status: String,
    val data: List<WasteThumbnail>,
    val meta: MetaPagination
)

data class MetaPagination(
    val page:Int,
    val limit:Int,
    @SerializedName("total_items")
    val totalItems:Int,
    @SerializedName("total_pages")
    val totalPages:Int
)