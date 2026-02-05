package com.example.saferecycle.data.model

data class Waste(
    val id: Int,
    val name: String,
    val imagePath: String,
    val category: Category,
    val isReusable: Boolean,
    val isRecyclable: Boolean,
    val isHazardous: Boolean,
    val description: String,
    val recycleTips: String
)