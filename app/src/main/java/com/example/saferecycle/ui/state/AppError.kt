package com.example.saferecycle.ui.state

sealed class AppError : Throwable() {
    data class Network(override val message: String) : AppError()
    data class Unauthorized(override val message: String) : AppError()
    data class Forbidden(override val message: String) : AppError()
    data class NotFound(override val message: String) : AppError()
    data class Server(override val message: String) : AppError()
    data class Unknown(override val message: String) : AppError()
    data class Format(override val message: String): AppError()
}