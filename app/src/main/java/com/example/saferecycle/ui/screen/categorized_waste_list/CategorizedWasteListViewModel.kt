package com.example.saferecycle.ui.screen.categorized_waste_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.saferecycle.data.repository.WasteRepository
import com.example.saferecycle.navigation.nav_graph.CategorizedWasteList
import com.example.saferecycle.navigation.nav_graph.WasteDetails
import com.example.saferecycle.ui.screen.WastePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategorizedWasteListViewModel @Inject constructor(
    private val wasteRepository: WasteRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val args = CategorizedWasteList.from(savedStateHandle)

    val pagingFlow = Pager(
        config = PagingConfig(
            pageSize = 5,
            initialLoadSize = 5,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            WastePagingSource(
                repository = wasteRepository,
                name = null,
                categoryId = args.categoryId
            )
        }
    ).flow.cachedIn(viewModelScope)
}