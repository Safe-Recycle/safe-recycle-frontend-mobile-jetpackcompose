package com.example.saferecycle.data.network

import com.example.saferecycle.ui.state.SessionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    tokenManager: TokenManager
) {
    private val _sessionState =
        MutableStateFlow(
            if (tokenManager.getToken() != null)
                SessionState.LoggedIn
            else
                SessionState.LoggedOut
        )

    val sessionState: StateFlow<SessionState> = _sessionState

    fun onLoginSuccess() {
        _sessionState.value = SessionState.LoggedIn
    }

    fun logout() {
        _sessionState.value = SessionState.LoggedOut
    }
}