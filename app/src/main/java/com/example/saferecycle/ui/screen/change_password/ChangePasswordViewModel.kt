package com.example.saferecycle.ui.screen.change_password

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saferecycle.data.model.ChangeUserPassword
import com.example.saferecycle.data.network.DataResult
import com.example.saferecycle.data.repository.UserRepository
import com.example.saferecycle.navigation.nav_graph.ChangePassword
import com.example.saferecycle.ui.state.AppError
import com.example.saferecycle.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val userRepository: UserRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val changePasswordArgs = ChangePassword.from(savedStateHandle)

    val userId = changePasswordArgs.userId
    private val _updateUserPassword =
        MutableStateFlow<UiState<String>>(UiState.Idle)
    val updateUserPassword = _updateUserPassword.asStateFlow()

    fun changeUserPassword(
        changeUserPassword: ChangeUserPassword
    ) {
        if (isFieldEmpty(changeUserPassword)) {
            _updateUserPassword.value =
                UiState.Error(error = AppError.Format("All Field must be filled"))
            return
        }
        if (!isPasswordEqual(
                password = changeUserPassword.newPassword,
                repeatPassword = changeUserPassword.confirmPassword
            )
        ) {
            _updateUserPassword.value =
                UiState.Error(error = AppError.Format("Password do not match"))
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            _updateUserPassword.value = UiState.Loading
            when (val result = userRepository.updateUserPassword(
                userId = userId,
                request =
                    hashMapOf(
                        "old_password" to changeUserPassword.oldPassword,
                        "password" to changeUserPassword.newPassword,
                        "password_confirm" to changeUserPassword.confirmPassword
                    )
            )) {
                is DataResult.Success<*> -> {
                    _updateUserPassword.value =
                        UiState.Success("Update Password Success, please login")

                }

                is DataResult.Error -> _updateUserPassword.value =
                    UiState.Error(result.error)

                else -> {
                    _updateUserPassword.value =
                        UiState.Error(AppError.Unknown("Unknown Error"))
                }
            }
        }
    }

    fun isFieldEmpty(changeUserPassword: ChangeUserPassword): Boolean {
        return changeUserPassword.oldPassword.isBlank() || changeUserPassword.newPassword.isBlank() || changeUserPassword.confirmPassword.isBlank()
    }

    fun isPasswordEqual(
        password: String,
        repeatPassword: String
    ): Boolean {
        return password == repeatPassword
    }
}