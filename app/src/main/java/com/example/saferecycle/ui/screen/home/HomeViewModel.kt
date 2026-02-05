package com.example.saferecycle.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saferecycle.data.model.Category
import com.example.saferecycle.data.model.User
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
class HomeViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val wasteRepository: WasteRepository,
    private val authRepository: AuthRepository

) : ViewModel() {
    private val _user = MutableStateFlow<Resource<User>>(Resource.Idle())
    val user = _user

    private val _categories =
        MutableStateFlow<Resource<List<Category>>>(Resource.Idle())
    val categories = _categories

    private val _suggestedWastes =
        MutableStateFlow<Resource<List<Waste>>>(Resource.Idle())
    val suggestedWastes = _suggestedWastes

    private val _popularWastes =
        MutableStateFlow<Resource<List<Waste>>>(Resource.Idle())
    val popularWastes = _popularWastes

    fun getUserData(){
        viewModelScope.launch(Dispatchers.IO) {
            _user.value = Resource.Loading()
            delay(2000)
            _user.value = authRepository.getUserData()
        }
    }

    fun getDummyCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            _categories.value = Resource.Loading()
            delay(2000)
            _categories.value = categoryRepository.getDummyCategory()
        }
    }

    fun getDummySuggestedWastes() {
        viewModelScope.launch(Dispatchers.IO) {
            _suggestedWastes.value = Resource.Loading()
            delay(2000)
            _suggestedWastes.value = wasteRepository.getDummySuggestedWaste()
        }
    }

    fun getDummyPopularWastes() {
        viewModelScope.launch(Dispatchers.IO) {
            _popularWastes.value = Resource.Loading()
            delay(2000)
            _popularWastes.value = wasteRepository.getDummyPopularWaste()
        }
    }
}