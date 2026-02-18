package com.example.saferecycle.ui.screen.category

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.saferecycle.data.dummyCategories
import com.example.saferecycle.data.network.Resource
import com.example.saferecycle.ui.component.CategoryCard
import com.example.saferecycle.ui.component.CategoryCardSkeleton
import com.example.saferecycle.ui.component.LostConnectionBottomSheet
import com.example.saferecycle.ui.component.TopBar
import com.example.saferecycle.ui.screen.home.HomeViewModel
import com.example.saferecycle.ui.state.AppError
import com.example.saferecycle.ui.state.UiState
import com.example.saferecycle.ui.theme.SafeRecycleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    onNavigateToCategoryWasteList: (String, Int) -> Unit,
    onBackClick: () -> Unit,
    vm: CategoryViewModel = hiltViewModel(),
) {
    val categoriesState by vm.categories.collectAsState()

    var showBottomSheet by remember { mutableStateOf(false) }
    val state = rememberPullToRefreshState()

    LaunchedEffect(Unit) {
//        vm.getDummyCategories()
        vm.getCategories()
    }
    Scaffold(
        topBar = {
            TopBar(
                text = "Waste Category",
                onBackClick = { onBackClick() }
            )
        }
    ) { innerPadding ->

        PullToRefreshBox(
            modifier = Modifier.padding(innerPadding),
            isRefreshing = categoriesState is UiState.Loading,
            onRefresh = { vm.getCategories() },
            state = state,
            indicator = {
                Indicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    isRefreshing = categoriesState is UiState.Loading,
                    containerColor = SafeRecycleTheme.colors.background,
                    color = SafeRecycleTheme.colors.accent,
                    state = state
                )
            }
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(16.dp),
                columns = GridCells.Adaptive(minSize = 75.dp),
                horizontalArrangement = spacedBy(17.dp),
                verticalArrangement = spacedBy(31.dp)
            ) {
                when (categoriesState) {
                    is UiState.Loading -> {
                        items(12) {
                            CategoryCardSkeleton()
                        }
                    }

                    is UiState.Success -> {
                        val categories =
                            (categoriesState as UiState.Success).data
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

                    is UiState.Error -> {
                        items(12) {
                            CategoryCardSkeleton()
                        }
                        val errorState =
                            (categoriesState as UiState.Error).error
                        when (errorState) {
                            is AppError.Network -> {
                                showBottomSheet = true
                            }

                            else -> {}
                        }
                    }

                    else -> {}
                }
            }
            if (showBottomSheet) {
                LostConnectionBottomSheet(
                    onTryAgainClick = {
                        vm.getCategories()
                        showBottomSheet = false
                    },
                    onDismissRequest = { showBottomSheet = false }
                )
            }
        }
    }
}