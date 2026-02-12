package com.example.saferecycle.data.repository

import android.content.Context
import android.util.Log
import com.example.saferecycle.data.network.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import androidx.core.content.edit
import com.example.saferecycle.data.dummyUser
import com.example.saferecycle.data.model.AuthResponse
import com.example.saferecycle.data.model.ErrorResponse
import com.example.saferecycle.data.model.User
import com.example.saferecycle.ui.state.AppError
import com.example.saferecycle.data.network.DataResult
import com.example.saferecycle.data.network.SessionManager
import com.example.saferecycle.data.network.TokenManager
import com.example.saferecycle.data.network.api_service.AuthApiService
import com.example.saferecycle.di.RefreshClient
import com.google.gson.Gson
import retrofit2.Response
import java.io.IOException

class AuthRepository @Inject constructor(
//    @ApplicationContext private val context: Context,
    private val api: AuthApiService,
    private val tokenManager: TokenManager,
    private val sessionManager: SessionManager
) : BaseRepository() {
//    private val api = RetrofitClient.getInstance(context).authApi
//    private val tokenManager = TokenManager(context)
//    private val sessionManager = SessionManager(tokenManager)

    //    fun isLoggedIn(): Boolean {
//        return tokenManager.getToken() != null
//    }
    suspend fun login2(
        email: String,
        password: String
    ): DataResult<Any> {

        val result = safeApiCall {
            api.login(email, password)
        }

        return when (result) {
            is DataResult.Success -> {
                tokenManager.saveToken(result.data.accessToken)
                tokenManager.saveRefreshToken(result.data.refreshToken)
                sessionManager.onLoginSuccess()
                DataResult.Success(Unit)
            }

            else -> result
        }
    }

    suspend fun login(
        email: String,
        password: String
    ): Resource<String> {
        return try {
            val response = api.login(email = email, password = password)

            if (response.isSuccessful) {
                val body = response.body()
                    ?: return Resource.Empty("Empty response")

                val token = body.accessToken

                val refreshToken = body.refreshToken

                Log.d("Login", "token:$token")
                Log.d("Login", "refresh_token:$refreshToken")

                tokenManager.saveToken(token)
                tokenManager.saveRefreshToken(refreshToken)
//                saveToken(token)
//                saveRefreshToken(refreshToken)

                Resource.Success("Login Success")
            } else {
                // ⬇️ INI PENTING
                val errorMessage = parseErrorMessage(response)
                Resource.Error(errorMessage)
            }
        } catch (e: IOException) {
            Resource.Error("No internet connection")
        } catch (e: Exception) {
            Resource.Error("Unexpected error")
        }
    }

    suspend fun logout(
    ): Resource<String> {
        return try {
            val token = tokenManager.getToken()
            val refreshToken = tokenManager.getRefreshToken()
            if (token.isNullOrEmpty() || refreshToken.isNullOrEmpty()) {
//                deleteToken()
                tokenManager.clear()
                return Resource.Empty("Empty Token")
            } else {
                val refreshTokenRequest =
                    hashMapOf("refresh_token" to refreshToken)
                val response = api.logout(token = "",request = refreshTokenRequest)

                if (response.isSuccessful) {
                    val body = response.body()
                        ?: return Resource.Error("Empty response")
                    val logoutMessage = body.message
//                    deleteToken()
                    tokenManager.clear()
                    Log.d("Logout", "token:${tokenManager.getToken()}")
                    Log.d(
                        "Logout",
                        "refresh_token:${tokenManager.getRefreshToken()}"
                    )
                    Resource.Success(logoutMessage)
                } else {
                    val errorMessage = parseErrorMessage(response)
//                    deleteToken()
                    tokenManager.clear()
                    Resource.Error(errorMessage)
                }
            }
        } catch (e: IOException) {
//            deleteToken()
            tokenManager.clear()
            Resource.Error("No internet connection")
        } catch (e: Exception) {
//            deleteToken()
            tokenManager.clear()
            Resource.Error("Unexpected error")
        }
    }

    fun getUserData(): Resource<User> {
        return try {
            Resource.Success(dummyUser)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

    private fun parseErrorMessage(
        response: Response<*>
    ): String {
        return try {
            val errorBody = response.errorBody()?.string()
                ?: return "Unknown error"

            val errorResponse = Gson().fromJson(
                errorBody,
                ErrorResponse::class.java
            )
            errorResponse.message
        } catch (e: Exception) {
            when (response.code()) {
                401 -> "Unauthorized"
                500 -> "Internal Server Error"
                else -> "Something went wrong"
            }
        }
    }
}