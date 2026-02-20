package com.example.saferecycle.data.repository

import android.content.Context
import com.example.saferecycle.data.dummyWastes
import com.example.saferecycle.data.model.Waste
import com.example.saferecycle.data.network.DataResult
import com.example.saferecycle.data.network.Resource
import com.example.saferecycle.data.network.api_service.UserApiService
import com.example.saferecycle.data.network.api_service.WasteApiService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WasteRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val api: WasteApiService,
) : BaseRepository() {
    suspend fun getDummySuggestedWaste(): Resource<List<Waste>> {
        return try {
            Resource.Success(dummyWastes)
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

    suspend fun getDummyPopularWaste(): Resource<List<Waste>> {
        return try {
            Resource.Success(dummyWastes)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

    suspend fun getDummyCategorizedWastes(categoryId: Int): Resource<List<Waste>> {
        return try {
            if (categoryId == 12) {
                Resource.Empty("Empty Token")
            } else {
                Resource.Success(dummyWastes)
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

    suspend fun searchWaste(keyword: String): Resource<List<Waste>> {
        return try {
            if (keyword == "kosong") {
                Resource.Empty("Empty Token")
            } else {
                Resource.Success(dummyWastes)
            }
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

    suspend fun getWasteDetailsDummy(wasteId: Int): Resource<Waste> {
        return try {
            Resource.Success(dummyWastes[wasteId - 1])
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }

    }

    suspend fun getWasteDetails(wasteId: Int): DataResult<Waste> {
        val result = safeApiCall { api.getWasteDetails(wasteId) }
        return when(result){
            is DataResult.Success -> {
                DataResult.Success(result.data.data)
            }
            is DataResult.Error -> {
                DataResult.Error(result.error)
            }
            else -> {
                DataResult.Empty
            }
        }

    }

}