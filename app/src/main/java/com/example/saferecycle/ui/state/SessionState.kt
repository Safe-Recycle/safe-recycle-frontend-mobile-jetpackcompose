package com.example.saferecycle.ui.state

sealed class SessionState {
    object LoggedIn : SessionState()
    object LoggedOut : SessionState()
}