package com.example.saferecycle.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saferecycle.data.model.AuthResponse
import com.example.saferecycle.data.model.User
import com.example.saferecycle.ui.state.AppError
import com.example.saferecycle.data.network.DataResult
import com.example.saferecycle.data.network.SessionManager
import com.example.saferecycle.ui.state.UiState
import com.example.saferecycle.data.repository.AuthRepository2
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel2 @Inject constructor(
    private val repository: AuthRepository2,
    private val sessionManager: SessionManager
) : ViewModel() {
    val sessionState = sessionManager.sessionState

    private val _loginState =
        MutableStateFlow<UiState<String>>(UiState.Idle)
    val loginState: StateFlow<UiState<String>> = _loginState

    private val _logoutState = MutableStateFlow<UiState<String>>(UiState.Idle)
    val logoutState: StateFlow<UiState<String>> = _logoutState

    private val _registerState = MutableStateFlow<UiState<String>>(UiState.Idle)
    val registerState: StateFlow<UiState<String>> = _registerState

    fun login(email: String, password: String) {
        if (isLoginFieldEmpty(email = email, password = password)) {
            _loginState.value =
                UiState.Error(error = AppError.Format("All field must be filled"))
            return
        }
        if (!isEmailValid(email = email)) {
            _loginState.value =
                UiState.Error(error = AppError.Format("Email format is not valid"))
            return
        }
        viewModelScope.launch {
            _loginState.value = UiState.Loading
            when (val result = repository.login(email, password)) {
                is DataResult.Success -> {
                    _loginState.value = UiState.Success("")
                }

                is DataResult.Error -> {
                    _loginState.value = UiState.Error(result.error)
                }

                else -> {}
            }
        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            _logoutState.value = UiState.Loading
            when (val result = repository.logout()) {
                is DataResult.Success -> {
                    _logoutState.value =
                        UiState.Success(data = result.data.message)
                }

                is DataResult.Error -> {
                    _logoutState.value = UiState.Error(result.error)
                }

                is DataResult.Empty -> _logoutState.value = UiState.Empty
            }
        }
    }

    fun register(
        email: String,
        name: String,
        password: String,
        repeatPassword: String
    ) {

        if (isRegisterFieldEmpty(email, name, password, repeatPassword)) {
            _registerState.value =
                UiState.Error(error = AppError.Format("All field must be filled"))
            return
        }
        if (!isEmailValid(email = email)) {
            _registerState.value =
                UiState.Error(error = AppError.Format("Email format is not valid"))
            return
        }
        if (!isPasswordEqual(password, repeatPassword)) {
            _registerState.value =
                UiState.Error(error = AppError.Format("Password do not match"))
            return
        }
        viewModelScope.launch {
            _registerState.value = UiState.Loading
            val user = User(
                id = 0,
                name = name,
                email = email,
                password = password
            )
            when (val result = repository.register(user)) {
                is DataResult.Success -> {
                    _registerState.value =
                        UiState.Success("Register success, please login")
                }

                is DataResult.Error -> {
                    _registerState.value = UiState.Error(result.error)
                }

                else -> {}
            }
        }
    }

    fun isLoginFieldEmpty(email: String, password: String): Boolean {
        return email.isBlank() || password.isBlank()
    }

    fun isEmailValid(email: String): Boolean {
        val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        return regex.matches(email)
    }

    fun isRegisterFieldEmpty(
        email: String,
        name: String,
        password: String,
        repeatPassword: String
    ): Boolean {
        return email.isBlank() || name.isBlank() || password.isBlank() || repeatPassword.isBlank()
    }

    fun isPasswordEqual(
        password: String,
        repeatPassword: String
    ): Boolean {
        return password == repeatPassword
    }
}