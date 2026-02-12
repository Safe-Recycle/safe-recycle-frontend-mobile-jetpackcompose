package com.example.saferecycle.ui.screen.waste_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saferecycle.data.model.Category
import com.example.saferecycle.data.model.Waste
import com.example.saferecycle.data.network.Resource
import com.example.saferecycle.data.repository.CategoryRepository
import com.example.saferecycle.data.repository.WasteRepository
import com.example.saferecycle.navigation.nav_graph.WasteList
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
        MutableStateFlow<Resource<List<Waste>>>(Resource.Idle())
    val wastes = _wastes

    suspend fun getWastes(){
        viewModelScope.launch(Dispatchers.IO) {
            when (wasteListArgs.source) {
                "Suggested for You" -> getSuggestedWastes()
                "Popular Waste" -> getPopularWastes()
//                "Category" -> {
//                    requireNotNull(wasteListArgs.categoryId)
//                    getCategorizedWastes(wasteListArgs.categoryId)
//                }
                else -> {
//                    error("Unknown source: ${wasteListArgs.source})")
                    requireNotNull(wasteListArgs.categoryId)
                    getCategorizedWastes(wasteListArgs.categoryId)
                }
            }
        }
    }

//    private val _categorizedWastes =
//        MutableStateFlow<Resource<List<Waste>>>(Resource.Idle())
//    val categorizedWastes = _categorizedWastes
//
//    private val _popularWastes =
//        MutableStateFlow<Resource<List<Waste>>>(Resource.Idle())
//    val popularWastes = _popularWastes
//
//    private val _suggestedWastes =
//        MutableStateFlow<Resource<List<Waste>>>(Resource.Idle())
//    val suggestedWastes = _suggestedWastes

    suspend fun getCategorizedWastes(categoryId: Int) {
        _wastes.value = Resource.Loading()
        delay(2000)
        _wastes.value =
            wasteRepository.getDummyCategorizedWastes(categoryId)
    }

    suspend fun getSuggestedWastes() {
        _wastes.value = Resource.Loading()
        delay(2000)
        _wastes.value = wasteRepository.getDummySuggestedWaste()
    }

    suspend fun getPopularWastes() {
        _wastes.value = Resource.Loading()
        delay(2000)
        _wastes.value =
            wasteRepository.getDummyPopularWaste()
    }
}