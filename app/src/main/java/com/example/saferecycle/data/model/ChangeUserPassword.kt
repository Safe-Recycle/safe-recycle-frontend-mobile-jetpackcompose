package com.example.saferecycle.data.model

data class ChangeUserPassword(
    val oldPassword:String,
    val newPassword:String,
    val confirmPassword:String
)