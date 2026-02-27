package com.example.saferecycle.data.model

import com.google.gson.annotations.SerializedName

data class WasteThumbnail(
    val id: Int,
    val name: String,
    @SerializedName("image_link")
    val imageLink: String,
    @SerializedName("category_name")
    val categoryName: String
)