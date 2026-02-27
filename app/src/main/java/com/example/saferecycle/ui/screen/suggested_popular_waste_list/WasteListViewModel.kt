package com.example.saferecycle.ui.screen.suggested_popular_waste_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saferecycle.data.model.Category
import com.example.saferecycle.data.model.Waste
import com.example.saferecycle.data.model.WasteThumbnail
import com.example.saferecycle.data.model.WasteThumbnailPopular
import com.example.saferecycle.data.network.DataResult
import com.example.saferecycle.data.network.Resource
import com.example.saferecycle.data.repository.WasteRepository
import com.example.saferecycle.navigation.nav_graph.WasteList
import com.example.saferecycle.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WasteListViewModel @Inject constructor(
    private val wasteRepository: WasteRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val wasteListArgs = WasteList.from(savedStateHandle)

    private val _wastes =
        MutableStateFlow<UiState<List<WasteThumbnail>>>(UiState.Idle)
    val wastes = _wastes

    fun getWastes() {
        when (wasteListArgs.wasteListSource) {
            WasteListSource.Suggested -> getSuggestedWastes()
            WasteListSource.Popular -> getPopularWastes()
        }
    }

    fun getSuggestedWastes() {
        _wastes.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {

            when (val result =
                wasteRepository.getSuggestedWaste(wasteListArgs.userId)) {
                is DataResult.Success -> _wastes.value =
                    UiState.Success(result.data)

                is DataResult.Error -> _wastes.value =
                    UiState.Error(result.error)

                is DataResult.Empty -> _wastes.value = UiState.Empty
            }

        }
    }

    fun getPopularWastes() {
        _wastes.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = wasteRepository.getPopularWaste()) {
                is DataResult.Success -> _wastes.value = UiState.Success(result.data)

                is DataResult.Error -> _wastes.value =
                    UiState.Error(result.error)

                is DataResult.Empty -> _wastes.value = UiState.Empty
            }
        }
    }
}