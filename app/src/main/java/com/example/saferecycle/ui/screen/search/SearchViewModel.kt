package com.example.saferecycle.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.saferecycle.data.model.Waste
import com.example.saferecycle.data.network.Resource
import com.example.saferecycle.data.repository.WasteRepository
import com.example.saferecycle.ui.screen.WastePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val wasteRepository: WasteRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    fun onQueryChange(newQuery: String) {
        _query.value = newQuery
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val pagingFlow = _query
        .debounce(500)
        .distinctUntilChanged()
        .flatMapLatest { keyword ->

            Pager(
                config = PagingConfig(
                    pageSize = 6,
                    initialLoadSize = 6,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = {
                    WastePagingSource(
                        repository = wasteRepository,
                        name = keyword.takeIf { it.isNotBlank() }, // <- ini penting
                        categoryId = null
                    )
                }
            ).flow
        }
        .cachedIn(viewModelScope)
}