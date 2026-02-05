package com.example.saferecycle.data.repository

import android.content.Context
import com.example.saferecycle.data.dummyUser
import com.example.saferecycle.data.model.User
import com.example.saferecycle.data.network.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend fun getUserData(): Resource<User> {
        return try {
            Resource.Success(dummyUser)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }
}