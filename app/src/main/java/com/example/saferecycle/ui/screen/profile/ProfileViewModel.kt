package com.example.saferecycle.ui.screen.profile


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saferecycle.data.network.DataResult
import com.example.saferecycle.data.repository.UserRepository
import com.example.saferecycle.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.saferecycle.data.model.User
import com.example.saferecycle.ui.state.AppError
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


@OptIn(FlowPreview::class)
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _user = MutableStateFlow<UiState<User>>(UiState.Idle)
    val user = _user

    private val _userId = MutableStateFlow<Int>(0)
    val userId = _userId

    private val _updateUserData =
        MutableStateFlow<UiState<String>>(UiState.Idle)
    val updateUserData = _updateUserData.asStateFlow()

    fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            _user.value = UiState.Loading
            when (val result = userRepository.getUserData()) {
                is DataResult.Success -> {
                    _userId.value = result.data.id
                    _user.value = UiState.Success(result.data)
                }

                is DataResult.Error -> {
                    _user.value = UiState.Error(result.error)
                }

                else -> {}
            }
        }
    }

    fun updateUserData(
        request: HashMap<String, String>
    ) {

        val name = request["name"]
        val email = request["email"]


        if (name != null && email != null) {
            if (isFieldEmpty(email, name)) {
                _updateUserData.value =
                    UiState.Error(error = AppError.Format("All Field must be filled"))
                return
            }
        }
        if (name != null) {
            if (name.isBlank()) {
                _updateUserData.value =
                    UiState.Error(error = AppError.Format("Name field must be filled"))
                return
            } else {
                request["name"] = name
            }
        }

        if (email != null) {
            if (email.isBlank()) {
                _updateUserData.value =
                    UiState.Error(error = AppError.Format("Email field must be filled"))
                return
            } else {
                if (isEmailValid(email)) {
                    request["email"] = email
                } else {
                    _updateUserData.value =
                        UiState.Error(error = AppError.Format("Email format is not valid"))
                    return
                }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            _updateUserData.value = UiState.Loading
            when (val result = userRepository.updateUserData(
                userId = _userId.value,
                request = request
            )) {
                is DataResult.Success<*> -> {
                    _updateUserData.value =
                        UiState.Success("Update Data Success")
                    getUserData()
                }

                is DataResult.Error -> _updateUserData.value =
                    UiState.Error(result.error)

                else -> {
                    _updateUserData.value =
                        UiState.Error(AppError.Unknown("Unknown Error"))
                }
            }
        }
    }

    fun getInitials(name: String): String {
        return name
            .trim()
            .split("\\s+".toRegex())      // pisah berdasarkan spasi berlebih
            .filter { it.isNotEmpty() }
            .take(2).joinToString("") { it.first().uppercase() }
    }

    fun isFieldEmpty(email: String, name: String): Boolean {
        return email.isBlank() || name.isBlank()
    }

    fun isEmailValid(email: String): Boolean {
        val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        return regex.matches(email)
    }
}