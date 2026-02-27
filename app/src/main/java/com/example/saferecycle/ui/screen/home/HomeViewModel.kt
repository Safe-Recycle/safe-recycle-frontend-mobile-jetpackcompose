package com.example.saferecycle.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saferecycle.data.model.Category
import com.example.saferecycle.data.model.User
import com.example.saferecycle.data.model.Waste
import com.example.saferecycle.data.model.WasteThumbnail
import com.example.saferecycle.data.model.WasteThumbnailPopular
import com.example.saferecycle.data.network.DataResult
import com.example.saferecycle.data.network.Resource
import com.example.saferecycle.data.repository.AuthRepository
import com.example.saferecycle.data.repository.CategoryRepository
import com.example.saferecycle.data.repository.UserRepository
import com.example.saferecycle.data.repository.WasteRepository
import com.example.saferecycle.ui.state.UiState
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
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _user = MutableStateFlow<UiState<User>>(UiState.Idle)
    val user = _user

    private var _userId = MutableStateFlow(0)
    val userId = _userId

    private val _categories = MutableStateFlow<UiState<List<Category>>>(UiState.Idle)
    val categories = _categories

    private val _suggestedWastes = MutableStateFlow<UiState<List<WasteThumbnail>>>(UiState.Idle)
    val suggestedWastes = _suggestedWastes

    private val _popularWastes = MutableStateFlow<UiState<List<WasteThumbnail>>>(UiState.Idle)
    val popularWastes = _popularWastes


    fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            _user.value = UiState.Loading
            when (val result = userRepository.getUserData()) {
                is DataResult.Success -> {
                    _user.value = UiState.Success(result.data)
                    _userId.value = result.data.id
                }

                is DataResult.Error -> {
                    _user.value = UiState.Error(result.error)
                }

                else -> {}
            }
        }
    }

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

    fun getSuggestedWaste() {
        _suggestedWastes.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = wasteRepository.getSuggestedWaste(_userId.value)) {
                is DataResult.Success -> {
                    _suggestedWastes.value = UiState.Success(result.data)
                }
                is DataResult.Error -> {
                    _suggestedWastes.value = UiState.Error(result.error)

                }
                is DataResult.Empty -> {
                    _suggestedWastes.value = UiState.Empty
                }

            }
        }
    }

    fun getPopularWaste() {
        _popularWastes.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = wasteRepository.getPopularWaste()){
                is DataResult.Success -> {
                    _popularWastes.value = UiState.Success(result.data)
                }
                is DataResult.Error -> {
                    _popularWastes.value = UiState.Error(result.error)

                }
                is DataResult.Empty -> {
                    _popularWastes.value = UiState.Empty
                }
            }
        }
    }

//    fun getDummyCategories() {
//        viewModelScope.launch(Dispatchers.IO) {
//            _categories.value = Resource.Loading()
//            delay(2000)
//            _categories.value = categoryRepository.getDummyCategory()
//        }
//    }

//    fun getDummySuggestedWastes() {
//        viewModelScope.launch(Dispatchers.IO) {
//            _suggestedWastes.value = Resource.Loading()
//            delay(2000)
//            _suggestedWastes.value = wasteRepository.getDummySuggestedWaste()
//        }
//    }
//
//    fun getDummyPopularWastes() {
//        viewModelScope.launch(Dispatchers.IO) {
//            _popularWastes.value = Resource.Loading()
//            delay(2000)
//            _popularWastes.value = wasteRepository.getDummyPopularWaste()
//        }
//    }

    fun getInitials(name: String): String {
        return name
            .trim()
            .split("\\s+".toRegex())      // pisah berdasarkan spasi berlebih
            .filter { it.isNotEmpty() }
            .take(2).joinToString("") { it.first().uppercase() }
    }
}