package com.example.saferecycle.ui.state

sealed class AppError {
    data class Network(val message: String) : AppError()
    data class Unauthorized(val message: String) : AppError()
    data class Forbidden(val message: String) : AppError()
    data class NotFound(val message: String) : AppError()
    data class Server(val message: String) : AppError()
    data class Unknown(val message: String) : AppError()
    data class Format(val message: String): AppError()
}