package com.example.saferecycle.ui.screen.category

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.saferecycle.data.dummyCategories
import com.example.saferecycle.data.network.Resource
import com.example.saferecycle.ui.component.CategoryCard
import com.example.saferecycle.ui.component.CategoryCardSkeleton
import com.example.saferecycle.ui.component.TopBar
import com.example.saferecycle.ui.screen.home.HomeViewModel

@Composable
fun CategoryScreen(
    onNavigateToCategoryWasteList: (String, Int) -> Unit,
    onBackClick: () -> Unit,
    vm: CategoryViewModel = hiltViewModel(),
) {
    val categoriesState by vm.categories.collectAsState()
    LaunchedEffect(Unit) {
        vm.getDummyCategories()
    }
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
            when (categoriesState) {
                is Resource.Loading<*> -> {
                    items(12) {
                        CategoryCardSkeleton()
                    }
                }
                is Resource.Success<*> -> {
                    val categories = (categoriesState as Resource.Success).data
                    items(categories) { category ->
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
                else -> {}
            }
        }
    }
}