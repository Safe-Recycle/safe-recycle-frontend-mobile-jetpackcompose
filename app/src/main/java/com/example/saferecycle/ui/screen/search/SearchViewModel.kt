package com.example.saferecycle.ui.screen.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saferecycle.data.model.Waste
import com.example.saferecycle.data.network.Resource
import com.example.saferecycle.data.repository.WasteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val wasteRepository: WasteRepository,
) : ViewModel() {
    private val _query = MutableStateFlow("")
    val query = _query

    private val _searchResult =
        MutableStateFlow<Resource<List<Waste>>>(Resource.Idle())
    val searchResult = _searchResult

    init {
        observeSearch()
    }

    fun onQueryChange(newQuery: String) {
        _query.value = newQuery
    }
    private fun observeSearch() {
        viewModelScope.launch {
            _query
                .debounce(500) // ⏱ tunggu user berhenti ngetik 500ms
                .distinctUntilChanged()
                .filter { it.isNotBlank() }
                .collectLatest { keyword ->
                    _searchResult.value = Resource.Loading()
                    delay(500)
                    _searchResult.value =
                        wasteRepository.searchWaste(keyword)
                }
        }
    }
}