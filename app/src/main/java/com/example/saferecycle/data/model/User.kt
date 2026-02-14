package com.example.saferecycle.data.model

data class User(
    val id:Int,
    val name: String,
    val email: String,
    val password: String? = null
)