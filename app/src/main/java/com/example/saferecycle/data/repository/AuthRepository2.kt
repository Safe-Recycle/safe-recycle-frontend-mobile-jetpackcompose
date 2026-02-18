package com.example.saferecycle.data.repository

import com.example.saferecycle.data.model.AuthResponse
import com.example.saferecycle.data.model.LogoutMessage
import com.example.saferecycle.data.model.User
import com.example.saferecycle.data.network.DataResult
import com.example.saferecycle.data.network.SessionManager
import com.example.saferecycle.data.network.TokenManager
import com.example.saferecycle.data.network.api_service.AuthApiService
import javax.inject.Inject

class AuthRepository2 @Inject constructor(
    private val api: AuthApiService,
    private val tokenManager: TokenManager,
    private val sessionManager: SessionManager
) : BaseRepository() {

    suspend fun login(
        email: String,
        password: String
    ): DataResult<AuthResponse> {

        val result = safeApiCall { api.login(email, password) }

        return when (result) {
            is DataResult.Success -> {
                tokenManager.saveToken(result.data.accessToken)
                tokenManager.saveRefreshToken(result.data.refreshToken)
                sessionManager.onLoginSuccess()
                result
            }
            is DataResult.Error -> result
            is DataResult.Empty -> result
        }
    }

    suspend fun logout(): DataResult<LogoutMessage> {
        val token = tokenManager.getToken()
        val refreshToken = tokenManager.getRefreshToken()

        if (token.isNullOrEmpty() || refreshToken.isNullOrEmpty()) {
            tokenManager.clear()
            sessionManager.logout()
            return DataResult.Empty
        } else {
            val request =
                hashMapOf("refresh_token" to refreshToken)

            val result = safeApiCall {
                api.logout(token = "Bearer ${tokenManager.getToken()}", request)
            }
            tokenManager.clear()
            sessionManager.logout()
            return result
        }
    }

    suspend fun register(user: User): DataResult<User> {
        val result = safeApiCall { api.register(user) }
        return result
    }
}