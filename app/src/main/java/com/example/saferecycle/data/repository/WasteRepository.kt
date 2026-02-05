package com.example.saferecycle.data.repository

import android.content.Context
import com.example.saferecycle.data.dummyCategories
import com.example.saferecycle.data.dummyWastes
import com.example.saferecycle.data.model.Category
import com.example.saferecycle.data.model.Waste
import com.example.saferecycle.data.network.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WasteRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
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

    suspend fun getDummyCategorizedWastes(categoryId: Int): Resource<List<Waste>> {
        return try {
            if (categoryId == 12) {
                Resource.Empty()
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

    suspend fun searchWaste(keyword: String): Resource<List<Waste>> {
        return try {
            if (keyword == "kosong") {
                Resource.Empty()
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

    suspend fun getWasteDetails(wasteId: Int): Resource<Waste> {
        return try {
            Resource.Success(dummyWastes[wasteId - 1])
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }

    }
}