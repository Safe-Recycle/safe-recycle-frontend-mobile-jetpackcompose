package com.example.saferecycle.data.model

import com.google.gson.annotations.SerializedName

data class WasteThumbnailPopular(
    val id: Int,
    val name: String,
    @SerializedName("image_link")
    val imageLink: String,
    @SerializedName("category_id")
    val categoryId:Int,
    val category: WasteThumbnailPopularCategory
)

data class WasteThumbnailPopularCategory(
    val name:String
)