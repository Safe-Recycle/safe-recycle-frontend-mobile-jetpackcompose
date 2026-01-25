package com.example.saferecycle.ui.screen.category

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.saferecycle.data.dummyCategories
import com.example.saferecycle.ui.component.CategoryCard
import com.example.saferecycle.ui.component.TopBar

@Composable
fun CategoryScreen(
    onNavigateToCategoryWasteList: (String, Int) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                text = "Waste Category",
                onBackClick = { onBackClick() }
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            columns = GridCells.Adaptive(minSize = 75.dp),
            horizontalArrangement = spacedBy(17.dp),
            verticalArrangement = spacedBy(31.dp)
        ) {
            items(dummyCategories) { category ->
                CategoryCard(
                    category = category,
                    onClick = {
                        onNavigateToCategoryWasteList(
                            "${category.name} Category",
                            category.id
                        )
                    }
                )
            }
        }
    }
}