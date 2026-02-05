package com.example.saferecycle.ui.screen.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saferecycle.data.model.Category
import com.example.saferecycle.data.model.Waste
import com.example.saferecycle.data.network.Resource
import com.example.saferecycle.data.repository.AuthRepository
import com.example.saferecycle.data.repository.CategoryRepository
import com.example.saferecycle.data.repository.WasteRepository
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
        MutableStateFlow<Resource<List<Category>>>(Resource.Idle())
    val categories = _categories

    fun getDummyCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            _categories.value = Resource.Loading()
            delay(2000)
            _categories.value = categoryRepository.getDummyCategory()
        }
    }
}