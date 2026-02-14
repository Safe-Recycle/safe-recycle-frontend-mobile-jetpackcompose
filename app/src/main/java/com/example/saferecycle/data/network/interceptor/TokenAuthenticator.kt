package com.example.saferecycle.data.network.interceptor

import com.example.saferecycle.data.network.SessionManager
import com.example.saferecycle.data.network.TokenManager
import com.example.saferecycle.data.network.api_service.AuthApiService
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.internal.notifyAll
import okhttp3.internal.wait


class TokenAuthenticator(
    private val tokenManager: TokenManager,
    private val authApi: AuthApiService,
    private val sessionManager: SessionManager
) : Authenticator {

    @Volatile
    private var isRefreshing = false

    private val lock = Any()

    override fun authenticate(route: Route?, response: Response): Request? {

        // Jangan infinite loop
        if (responseCount(response) >= 2) {
            sessionManager.logout()
            return null
        }

        synchronized(lock) {

            // Kalau token sudah diperbarui oleh request lain
            val newToken = tokenManager.getToken()
            val requestToken =
                response.request.header("Authorization")?.replace("Bearer ", "")

            if (newToken != null && newToken != requestToken) {
                return response.request.newBuilder()
                    .header("Authorization", "Bearer $newToken")
                    .build()
            }

            // Kalau sedang ada refresh lain, tunggu
            if (isRefreshing) {
                lock.wait()
                val updatedToken = tokenManager.getToken() ?: return null

                return response.request.newBuilder()
                    .header("Authorization", "Bearer $updatedToken")
                    .build()
            }

            // Kita yang bertugas refresh
            isRefreshing = true

            val refreshToken = tokenManager.getRefreshToken()
                ?: return logoutAndAbort()

            val result = runBlocking {
                authApi.refreshToken(
                    hashMapOf("refresh_token" to refreshToken)
                )
            }

            isRefreshing = false
            lock.notifyAll()

            return if (result.isSuccessful) {
                val body = result.body()
                if (body == null) {
                    logoutAndAbort()
                } else {
                    val token = body.accessToken
                    val refreshToken = body.refreshToken
                    tokenManager.saveToken(token)
                    tokenManager.saveRefreshToken(refreshToken)

                    response.request.newBuilder()
                        .header("Authorization", "Bearer $token")
                        .build()
                }
            } else {
                logoutAndAbort()
            }
        }
    }

    private fun logoutAndAbort(): Request? {
        sessionManager.logout()
        return null
    }

    private fun responseCount(response: Response): Int {
        var count = 1
        var prior = response.priorResponse
        while (prior != null) {
            count++
            prior = prior.priorResponse
        }
        return count
    }
}