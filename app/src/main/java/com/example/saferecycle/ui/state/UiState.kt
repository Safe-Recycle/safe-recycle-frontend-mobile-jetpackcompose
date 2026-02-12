package com.example.saferecycle.ui.state

import com.example.saferecycle.ui.state.AppError

sealed class UiState<out T> {
    object Idle : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    object Empty : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val error: AppError) : UiState<Nothing>()
}