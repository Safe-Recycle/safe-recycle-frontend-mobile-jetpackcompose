package com.example.saferecycle.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Waste(
    val id: Int,
    val name: String,
    val description: String,
    @SerializedName("recycle")
    val recycleTips: String,
    @SerializedName("is_reusable")
    val isReusable: Boolean,
    @SerializedName("is_recyclable")
    val isRecyclable: Boolean,
    @SerializedName("is_hazardous")
    val isHazardous: Boolean,
    @SerializedName("image_link")
    val imagePath: String,
    val category: Category,
)