package com.example.saferecycle.ui.screen

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.saferecycle.data.model.WasteThumbnail
import com.example.saferecycle.data.network.DataResult
import com.example.saferecycle.data.repository.WasteRepository

class WastePagingSource(
    private val repository: WasteRepository,
    private val name: String?,
    private val categoryId: Int?
) : PagingSource<Int, WasteThumbnail>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, WasteThumbnail> {

        val page = params.key ?: 1
        val limit = 6

        return when (
            val result = repository.getWastePage(
                name = name,
                categoryId = categoryId,
                page = page,
                limit = limit
            )
        ) {
            is DataResult.Success -> {
                val response = result.data
                val items = response.data
                val meta = response.meta

                LoadResult.Page(
                    data = items,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (page < meta.totalPages) page + 1 else null
                )
            }

            is DataResult.Error -> {
                LoadResult.Error(result.error)
            }

            is DataResult.Empty -> {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        }
    }

    override fun getRefreshKey(
        state: PagingState<Int, WasteThumbnail>
    ): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}