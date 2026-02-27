package com.example.saferecycle.ui.screen.categorized_waste_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.saferecycle.ui.component.EmptyListIndicator
import com.example.saferecycle.ui.component.LostConnectionBottomSheet
import com.example.saferecycle.ui.component.TopBar
import com.example.saferecycle.ui.component.WasteCard
import com.example.saferecycle.ui.component.WasteCardSkeleton
import com.example.saferecycle.ui.state.AppError
import com.example.saferecycle.ui.theme.SafeRecycleTheme

@Composable
fun CategorizedWasteListScreen(
    categoryName: String,
    onBackClick: () -> Unit,
    onNavigateToDetailWaste: (Int) -> Unit,
    vm: CategorizedWasteListViewModel = hiltViewModel()
) {
    val lazyPagingItems = vm.pagingFlow.collectAsLazyPagingItems()
    val refreshState = lazyPagingItems.loadState.refresh

    var showBottomSheet by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullToRefreshState()

    var isUserRefreshing by remember { mutableStateOf(false) }

    LaunchedEffect(refreshState) {
        if (refreshState is LoadState.NotLoading ||
            refreshState is LoadState.Error
        ) {
            isUserRefreshing = false
        }
    }

    // Detect Network Error
    LaunchedEffect(refreshState) {
        if (refreshState is LoadState.Error) {
            if (refreshState.error is AppError.Network) {
                showBottomSheet = true
            }
        }
    }
    Scaffold(
        topBar = {
            TopBar(
                text = "$categoryName Category",
                onBackClick = { onBackClick() })
        }
    ) { innerPadding ->
        PullToRefreshBox(
            modifier = Modifier.padding(innerPadding),
            state = pullRefreshState,
            isRefreshing = isUserRefreshing && refreshState is LoadState.Loading,
            onRefresh = { lazyPagingItems.refresh() },
            indicator = {
                Indicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    isRefreshing = refreshState is LoadState.Loading,
                    containerColor = SafeRecycleTheme.colors.background,
                    color = SafeRecycleTheme.colors.accent,
                    state = pullRefreshState
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
            ) {
                // =============================
                // EMPTY STATE
                // =============================
                if (refreshState is LoadState.NotLoading &&
                    lazyPagingItems.itemCount == 0
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        EmptyListIndicator(
                            text = "Currently, there is no recorded waste\ncategorized here yet"
                        )
                    }
                } else {

                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxSize(),
                        columns = GridCells.Adaptive(163.dp),
                        horizontalArrangement = Arrangement.spacedBy(19.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        // First Load Skeleton
                        if (refreshState is LoadState.Loading) {
                            items(12) {
                                WasteCardSkeleton()
                            }
                        }

                        // Data
                        items(lazyPagingItems.itemCount) { index ->
                            lazyPagingItems[index]?.let { waste ->
                                WasteCard(
                                    waste = waste,
                                    onClick = {
                                        onNavigateToDetailWaste(waste.id)
                                    }
                                )
                            }
                        }

                        // Paging Loading
                        if (lazyPagingItems.loadState.append is LoadState.Loading) {
                            items(12) {
                                WasteCardSkeleton()
                            }
                        }
                    }
                }

                // BottomSheet Error
                if (showBottomSheet) {
                    LostConnectionBottomSheet(
                        onTryAgainClick = {
                            lazyPagingItems.refresh()
                            showBottomSheet = false
                        },
                        onDismissRequest = {
                            showBottomSheet = false
                        }
                    )
                }
            }
        }
    }
}