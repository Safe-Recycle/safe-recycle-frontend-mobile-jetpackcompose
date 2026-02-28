package com.example.saferecycle.ui.screen.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saferecycle.data.model.Category
import com.example.saferecycle.data.model.Waste
import com.example.saferecycle.data.network.DataResult
import com.example.saferecycle.data.network.Resource
import com.example.saferecycle.data.repository.CategoryRepository
import com.example.saferecycle.data.repository.WasteRepository
import com.example.saferecycle.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
) : ViewModel() {
    private val _categories =
        MutableStateFlow<UiState<List<Category>>>(UiState.Idle)
    val categories = _categories

    fun getCategories() {
        _categories.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = categoryRepository.getCategories()) {
                is DataResult.Success -> {
                    _categories.value = UiState.Success(result.data)
                }

                is DataResult.Error -> {
                    val error = result.error
                    _categories.value = UiState.Error(error)
                }

                is DataResult.Empty -> _categories.value = UiState.Empty
            }
        }
    }
}