package com.example.saferecycle.ui.screen.waste_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.saferecycle.data.model.Waste
import com.example.saferecycle.data.network.Resource
import com.example.saferecycle.data.repository.WasteRepository
import com.example.saferecycle.navigation.nav_graph.WasteDetails
import com.example.saferecycle.navigation.nav_graph.WasteList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class WasteDetailsViewModel @Inject constructor(
    private val wasteRepository: WasteRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val wasteDetailsArgs = WasteDetails.from(savedStateHandle)

    private val _wasteDetails =
        MutableStateFlow<Resource<Waste>>(Resource.Idle())
    val wasteDetails = _wasteDetails

    suspend fun getWasteDetails() {
        _wasteDetails.value = Resource.Loading()
        delay(2000)
        _wasteDetails.value =
            wasteRepository.getWasteDetails(wasteDetailsArgs.wasteId)
    }
}