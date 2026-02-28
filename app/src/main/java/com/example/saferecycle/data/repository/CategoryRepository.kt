package com.example.saferecycle.data.repository

import android.content.Context
import com.example.saferecycle.data.dummyCategories
import com.example.saferecycle.data.model.Category
import com.example.saferecycle.data.model.Waste
import com.example.saferecycle.data.network.DataResult
import com.example.saferecycle.data.network.Resource
import com.example.saferecycle.data.network.api_service.CategoryApiService
import com.example.saferecycle.data.network.api_service.UserApiService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val api: CategoryApiService,
): BaseRepository() {
    suspend fun getCategories(): DataResult<List<Category>>{
        val result = safeApiCall { api.getCategories() }
        return result
    }
}