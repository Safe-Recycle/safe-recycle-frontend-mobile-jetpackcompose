package com.example.saferecycle.ui.screen.suggested_popular_waste_list

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.paging.LoadState
import com.example.saferecycle.data.model.WasteThumbnail
import com.example.saferecycle.data.network.Resource
import com.example.saferecycle.ui.component.EmptyListIndicator
import com.example.saferecycle.ui.component.LostConnectionBottomSheet
import com.example.saferecycle.ui.component.TopBar
import com.example.saferecycle.ui.component.WasteCard
import com.example.saferecycle.ui.component.WasteCardSkeleton
import com.example.saferecycle.ui.state.AppError
import com.example.saferecycle.ui.state.UiState
import com.example.saferecycle.ui.theme.SafeRecycleTheme

@Composable
fun WasteListScreen(
    source: String,
    onBackClick: () -> Unit,
    onNavigateToDetailWaste: (Int) -> Unit,
    vm: WasteListViewModel = hiltViewModel()
) {
    val wastesState by vm.wastes.collectAsState()

    val state = rememberPullToRefreshState()
    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        vm.getWastes()
    }

    LaunchedEffect(wastesState) {
        if (wastesState is UiState.Error) {
            if ((wastesState as UiState.Error).error is AppError.Network) {
                showBottomSheet = true
            }
        }
    }
    Scaffold(
        topBar = {
            TopBar(
                text = source,
                onBackClick = { onBackClick() })
        }
    ) { innerPadding ->
        PullToRefreshBox(
            modifier = Modifier.padding(innerPadding),
            isRefreshing = wastesState is UiState.Loading,
            onRefresh = {
                vm.getWastes()
            },
            state = state,
            indicator = {
                Indicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    isRefreshing = wastesState is UiState.Loading,
                    containerColor = SafeRecycleTheme.colors.background,
                    color = SafeRecycleTheme.colors.accent,
                    state = state
                )
            }
        ) {
            when (wastesState) {
                is UiState.Empty -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        EmptyListIndicator(text = "Currently, there is no recorded waste\ncategorized here yet")
                    }
                }

                is UiState.Loading -> {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .padding(16.dp),
                        columns = GridCells.Adaptive(163.dp),
                        horizontalArrangement = spacedBy(19.dp),
                        verticalArrangement = spacedBy(15.dp)
                    ) {
                        items(12) { waste ->
                            WasteCardSkeleton()
                        }
                    }
                }

                is UiState.Success -> {
                    val wastes =
                        (wastesState as UiState.Success<List<WasteThumbnail>>).data
                    LazyVerticalGrid(
                        modifier = Modifier
                            .padding(16.dp),
                        columns = GridCells.Adaptive(163.dp),
                        horizontalArrangement = spacedBy(19.dp),
                        verticalArrangement = spacedBy(15.dp)
                    ) {
                        items(wastes) { waste ->
                            WasteCard(
                                waste = waste,
                                onClick = { onNavigateToDetailWaste(waste.id) })
                        }
                    }
                }

                is UiState.Error ->{
                    LazyVerticalGrid(
                        modifier = Modifier
                            .padding(16.dp),
                        columns = GridCells.Adaptive(163.dp),
                        horizontalArrangement = spacedBy(19.dp),
                        verticalArrangement = spacedBy(15.dp)
                    ) {
                        items(12) { waste ->
                            WasteCardSkeleton()
                        }
                    }
                }
                else -> {}
            }
            if (showBottomSheet) {
                LostConnectionBottomSheet(
                    onTryAgainClick = {
                        vm.getWastes()
                        showBottomSheet = false
                    },
                    onDismissRequest = { showBottomSheet = false }
                )
            }
        }
    }
}