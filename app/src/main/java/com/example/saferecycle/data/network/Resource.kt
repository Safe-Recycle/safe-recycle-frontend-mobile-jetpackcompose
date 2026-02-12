package com.example.saferecycle.data.network

sealed class Resource<T> {
    class Idle<T> : Resource<T>()
    class Loading<T> : Resource<T>()
    class Empty<T>(val message: String) : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val message: String) : Resource<T>()
}