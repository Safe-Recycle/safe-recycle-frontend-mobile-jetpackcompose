package com.example.saferecycle.ui.screen.waste_list

sealed class WasteListSource {
    data object Suggested : WasteListSource()
    data object Popular : WasteListSource()
    data class Category(val categoryId: Int) : WasteListSource()
}