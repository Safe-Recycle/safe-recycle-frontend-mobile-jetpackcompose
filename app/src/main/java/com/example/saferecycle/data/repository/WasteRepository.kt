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
    private val wasteApi: WasteApiService,
    private val historyApi: HistoryApiService,
) : BaseRepository() {

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

    suspend fun getPopularWaste(): DataResult<List<WasteThumbnail>> {
        val result = safeApiCall { historyApi.getPopularWaste(page = 1, limit = 6) }

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