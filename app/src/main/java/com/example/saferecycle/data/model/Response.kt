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