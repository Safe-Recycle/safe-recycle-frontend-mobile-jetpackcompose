package com.example.saferecycle.data.network

import com.example.saferecycle.ui.state.AppError

sealed class DataResult<out T> {
    data class Success<T>(val data: T) : DataResult<T>()
    object Empty : DataResult<Nothing>()
    data class Error(val error: AppError) : DataResult<Nothing>()
}