package com.example.saferecycle.data.repository

import android.content.Context
import com.example.saferecycle.data.dummyWastes
import com.example.saferecycle.data.model.ShowItemResponse
import com.example.saferecycle.data.model.Waste
import com.example.saferecycle.data.model.WasteThumbnail
import com.example.saferecycle.data.model.WasteThumbnailPopular
import com.example.saferecycle.data.network.DataResult
import com.example.saferecycle.data.network.Resource
import com.example.saferecycle.data.network.api_service.HistoryApiService
import com.example.saferecycle.data.network.api_service.WasteApiService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WasteRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val wasteApi: WasteApiService,
    private val historyApi: HistoryApiService,
) : BaseRepository() {
    suspend fun getDummySuggestedWaste(): Resource<List<Waste>> {
        return try {
            Resource.Success(dummyWastes)
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

    suspend fun searchDummyWaste(keyword: String): Resource<List<Waste>> {
        return try {
            if (keyword == "kosong") {
                Resource.Empty("Empty Token")
            } else {
                Resource.Success(dummyWastes)
            }
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
        val result = safeApiCall { wasteApi.getWasteDetails(wasteId) }
        return when (result) {
            is DataResult.Success -> DataResult.Success(result.data.data)
            is DataResult.Error -> DataResult.Error(result.error)
            else -> DataResult.Empty

        }
    }

    suspend fun getSuggestedWaste(userId: Int): DataResult<List<WasteThumbnail>> {
        val result = safeApiCall { historyApi.getSuggestedWaste(userId) }
        return when (result) {
            is DataResult.Success -> {
                val data = result.data.data
                if (data.isEmpty()) {
                    DataResult.Empty
                } else {
                    DataResult.Success(result.data.data)
                }
            }

            is DataResult.Error -> DataResult.Error(result.error)
            is DataResult.Empty -> DataResult.Empty
        }
    }

//    suspend fun getPopularWaste(): DataResult<List<WasteThumbnailPopular>> {
//        val result = safeApiCall { historyApi.getPopularWaste() }
//        return when (result) {
//            is DataResult.Success -> {
//                val data = result.data.data
//                if (data.isEmpty()) {
//                    DataResult.Empty
//                } else {
//                    DataResult.Success(data)
//                }
//            }
//
//            is DataResult.Error -> DataResult.Error(result.error)
//            is DataResult.Empty -> DataResult.Empty
//        }
//    }

    suspend fun getPopularWaste(): DataResult<List<WasteThumbnail>> {
        val result = safeApiCall { historyApi.getPopularWaste() }

        return when (result) {
            is DataResult.Success -> {
                val data = result.data.data

                if (data.isEmpty()) {
                    DataResult.Empty
                } else {
                    val mappedData = data.map { item ->
                        WasteThumbnail(
                            id = item.id,
                            name = item.name,
                            imageLink = item.imageLink,
                            categoryName = item.category.name
                        )
                    }
                    DataResult.Success(mappedData)
                }
            }

            is DataResult.Error -> DataResult.Error(result.error)
            is DataResult.Empty -> DataResult.Empty
        }
    }

    suspend fun getWasteList(
        name: String?,
        categoryId: Int?,
        page:Int,
        limit:Int
    ): DataResult<List<WasteThumbnail>> {
        val result = safeApiCall {
            wasteApi.getWasteList(
                name, category = categoryId,
                page = page,
                limit = limit
            )
        }
        return when (result) {
            is DataResult.Success -> {
                val data = result.data.data
                if (data.isEmpty()) {
                    DataResult.Empty
                } else {
                    DataResult.Success(result.data.data)
                }
            }

            is DataResult.Error -> DataResult.Error(result.error)
            is DataResult.Empty -> DataResult.Empty
        }

    }
    suspend fun getWasteListUpdated(
        name: String?,
        categoryId: Int?,
        page:Int,
        limit:Int
    ): DataResult<ShowItemResponse> {
        val result = safeApiCall {
            wasteApi.getWasteList(
                name, category = categoryId,
                page = page,
                limit = limit
            )
        }
        return when (result) {
            is DataResult.Success -> result
            is DataResult.Error -> result
            is DataResult.Empty -> result
        }

    }
    suspend fun getWastePage(
        name: String?,
        categoryId: Int?,
        page: Int,
        limit: Int
    ): DataResult<ShowItemResponse> {

        return safeApiCall {
            wasteApi.getWasteList(
                name = name,
                category = categoryId,
                page = page,
                limit = limit
            )
        }
    }
}