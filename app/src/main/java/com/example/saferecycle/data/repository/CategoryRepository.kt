package com.example.saferecycle.data.repository

import android.content.Context
import com.example.saferecycle.data.dummyCategories
import com.example.saferecycle.data.model.Category
import com.example.saferecycle.data.model.Waste
import com.example.saferecycle.data.network.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend fun getDummyCategory(): Resource<List<Category>> {
        return try {
            Resource.Success(dummyCategories)
            //val token = getToken() ?: ""
            //val response = api.getCompanies(token = "Bearer $token")
//            if (response.isSuccessful) {
//                val body = response.body()
//                if (body != null) {
//                    Resource.Success(body)
//                } else {
//                    Resource.Error("Empty response body")
//                }
//            } else {
//                Resource.Error("HTTP Error: ${response.code()}")
//            }

        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }
}