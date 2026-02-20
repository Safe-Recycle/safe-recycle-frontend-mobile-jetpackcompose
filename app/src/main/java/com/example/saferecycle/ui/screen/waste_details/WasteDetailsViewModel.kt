package com.example.saferecycle.ui.screen.waste_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saferecycle.data.model.Waste
import com.example.saferecycle.data.network.DataResult
import com.example.saferecycle.data.network.Resource
import com.example.saferecycle.data.repository.WasteRepository
import com.example.saferecycle.navigation.nav_graph.WasteDetails
import com.example.saferecycle.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WasteDetailsViewModel @Inject constructor(
    private val wasteRepository: WasteRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val wasteDetailsArgs = WasteDetails.from(savedStateHandle)

    private val _wasteDetails =
        MutableStateFlow<UiState<Waste>>(UiState.Idle)
    val wasteDetails = _wasteDetails

//    suspend fun getWasteDetailsDummy() {
//        _wasteDetails.value = Resource.Loading()
//        delay(2000)
//        _wasteDetails.value =
//            wasteRepository.getWasteDetailsDummy(wasteDetailsArgs.wasteId)
//    }

    fun getWasteDetails(wasteId: Int) {
        _wasteDetails.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = wasteRepository.getWasteDetails(wasteId)) {
                is DataResult.Success -> _wasteDetails.value =
                    UiState.Success(result.data)

                is DataResult.Error -> _wasteDetails.value =
                    UiState.Error(error = result.error)

                is DataResult.Empty -> _wasteDetails.value = UiState.Empty
            }
        }
    }

}