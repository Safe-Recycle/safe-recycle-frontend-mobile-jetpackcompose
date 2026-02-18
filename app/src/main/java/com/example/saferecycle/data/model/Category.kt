package com.example.saferecycle.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class Category(
    val id: Int,
    val name: String,
    @SerializedName("image_link") val imageLink: String
)