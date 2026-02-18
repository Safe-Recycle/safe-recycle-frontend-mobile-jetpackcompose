package com.example.saferecycle.data.repository

import android.util.Log
import com.example.saferecycle.data.model.BaseResponse
import com.example.saferecycle.data.model.User
import com.example.saferecycle.data.network.DataResult
import com.example.saferecycle.data.network.SessionManager
import com.example.saferecycle.data.network.TokenManager
import com.example.saferecycle.data.network.api_service.UserApiService
import okhttp3.Request
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApiService,
    private val tokenManager: TokenManager,
    private val sessionManager: SessionManager
) : BaseRepository() {
    suspend fun getUserData(): DataResult<User> {
        val result = safeApiCall { api.getUserData() }

        return when (result) {
            is DataResult.Success -> result
            is DataResult.Empty -> result
            is DataResult.Error -> result
        }
    }

    suspend fun updateUserData(
        userId: Int,
        request: HashMap<String, String>
    ): DataResult<BaseResponse<User>> {

        val result =
            safeApiCall {
                api.updateUserData(
                    userId = userId,
                    request = request
                )
            }
        return result
    }

    suspend fun updateUserPassword(
        userId: Int,
        request: HashMap<String, String>
    ): DataResult<BaseResponse<User>> {

        val result =
            safeApiCall {
                api.updateUserData(
                    userId = userId,
                    request = request
                )
            }
        return when(result){
            is DataResult.Success<*> -> {
                tokenManager.clear()
                sessionManager.logout()
                result
            }
            is DataResult.Error-> result
            is DataResult.Empty-> result
        }
    }
}