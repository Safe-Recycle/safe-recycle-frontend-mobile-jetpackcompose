package com.example.saferecycle.ui.screen.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.saferecycle.ui.component.EmptyListIndicator
import com.example.saferecycle.ui.component.LostConnectionBottomSheet
import com.example.saferecycle.ui.component.SearchField
import com.example.saferecycle.ui.component.TopBar
import com.example.saferecycle.ui.component.WasteCard
import com.example.saferecycle.ui.component.WasteCardSkeleton
import com.example.saferecycle.ui.state.AppError
import com.example.saferecycle.ui.theme.SafeRecycleTheme

@Composable
fun SearchScreen(
    onBackClick: () -> Unit,
    onNavigateToDetailWaste: (Int) -> Unit,
    vm: SearchViewModel = hiltViewModel()
) {

    val searchQuery by vm.query.collectAsState()
    val lazyPagingItems = vm.pagingFlow.collectAsLazyPagingItems()

    var showBottomSheet by remember { mutableStateOf(false) }

    val pullRefreshState = rememberPullToRefreshState()

    var isUserRefreshing by remember { mutableStateOf(false) }

    // =============================
    // DETECT NETWORK ERROR
    // =============================
    val refreshState = lazyPagingItems.loadState.refresh
    if (refreshState is LoadState.Error) {
        val error = refreshState.error
        if (error is AppError.Network) {
            showBottomSheet = true
        }
    }

    LaunchedEffect(refreshState) {
        if (refreshState is LoadState.NotLoading || refreshState is LoadState.Error
        ) {
            isUserRefreshing = false
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                text = "Search",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->

        PullToRefreshBox(
            modifier = Modifier.padding(innerPadding),
            state = pullRefreshState,
            isRefreshing = isUserRefreshing && refreshState is LoadState.Loading,
            onRefresh = {
                isUserRefreshing = true
                lazyPagingItems.refresh()
            },
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
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                SearchField(
                    modifier = Modifier
                        .clip(RoundedCornerShape(13.dp))
                        .height(45.dp),
                    value = searchQuery,
                    onValueChange = { vm.onQueryChange(it) }
                )

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
                            text = "Currently, there is no recorded waste\nmatching your search query. Try\nscanning the object"
                        )
                    }

                } else {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        columns = GridCells.Adaptive(163.dp),
                        horizontalArrangement = Arrangement.spacedBy(19.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {

                        // FIRST LOAD SKELETON
                        if (refreshState is LoadState.Loading) {
                            items(12) {
                                WasteCardSkeleton()
                            }
                        }

                        // DATA
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

                        // PAGING LOADING
                        if (lazyPagingItems.loadState.append is LoadState.Loading) {
                            items(4) {
                                WasteCardSkeleton()
                            }
                        }
                    }
                }
            }

            // =============================
            // NETWORK ERROR BOTTOM SHEET
            // =============================
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

//@Composable
//fun SearchScreen(
//    onBackClick: () -> Unit,
//    onNavigateToDetailWaste: (Int) -> Unit,
//    vm: SearchViewModel = hiltViewModel()
//) {
//    //var searchQuery by remember { mutableStateOf("") }
//    val searchQuery by vm.query.collectAsState()
//    val resultState by vm.searchResult.collectAsState()
//
//    Scaffold(
//        topBar = {
//            TopBar(
//                text = "Search",
//                onBackClick = { onBackClick() }
//            )
//        }
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .padding(innerPadding)
//                .padding(16.dp)
//                .fillMaxSize(),
//            verticalArrangement = spacedBy(12.dp)
//        ) {
//            SearchField(
//                modifier = Modifier
//                    .clip(RoundedCornerShape(13.dp))
//                    .height(45.dp),
//                value = searchQuery,
//                onValueChange = { vm.onQueryChange(it) }
//            )
//            when(resultState){
//                is Resource.Empty<*> -> {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxSize(),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        EmptyListIndicator(text = "Currently, there is no recorded waste\nmatching your search query. Try\nscanning the object")
//                    }
//                }
//                is Resource.Loading<*> -> {
//                    LazyVerticalGrid(
//                        modifier = Modifier,
//                        columns = GridCells.Adaptive(163.dp),
//                        horizontalArrangement = spacedBy(19.dp),
//                        verticalArrangement = spacedBy(15.dp)
//                    ) {
//                        items(12) { waste ->
//                            WasteCardSkeleton()
//                        }
//                    }
//                }
//                is Resource.Success<*> -> {
//                    val wastes =(resultState as Resource.Success).data
//
//                    LazyVerticalGrid(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .weight(1f),
//                        columns = GridCells.Adaptive(163.dp),
//                        horizontalArrangement = spacedBy(19.dp),
//                        verticalArrangement = spacedBy(15.dp)
//                    ) {
//                        items(wastes) { waste ->
////                            WasteCard(
////                                waste = waste,
////                                onClick = { onNavigateToDetailWaste(waste.id) }
////                            )
//                        }
//                    }
//                }
//
//                else -> {}
//            }
////            LazyVerticalGrid(
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .weight(1f),
////                columns = GridCells.Adaptive(163.dp),
////                horizontalArrangement = spacedBy(19.dp),
////                verticalArrangement = spacedBy(15.dp)
////            ) {
////                items(dummyWastes) { waste ->
////                    WasteCard(
////                        waste = waste,
////                        onClick = { onNavigateToDetailWaste(waste.id) }
////                    )
////                }
////            }
////
////            if (false/*emptyWasteList.isEmpty()*/) {
////                Box(
////                    modifier = Modifier
////                        .fillMaxSize(),
////                    contentAlignment = Alignment.BottomCenter
////                ) {
////                    EmptyListIndicator(text = "Currently, there is no recorded waste\nmatching your search query. Try\nscanning the object")
////                }
////            }
//        }
//    }
//}