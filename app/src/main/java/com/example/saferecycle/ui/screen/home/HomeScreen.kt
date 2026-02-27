package com.example.saferecycle.ui.screen.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.saferecycle.data.dummyWastes
import com.example.saferecycle.ui.component.SafeRecycleBottomNavBar
import com.example.saferecycle.ui.component.SearchField
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.saferecycle.data.network.Resource
import com.example.saferecycle.ui.component.LostConnectionBottomSheet
import com.example.saferecycle.ui.component.SearchFieldSkeleton
import com.example.saferecycle.ui.state.AppError
import com.example.saferecycle.ui.state.UiState
import com.example.saferecycle.ui.theme.SafeRecycleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToSearch: () -> Unit,
    onNavigateToCategory: () -> Unit,
    onNavigateToCategoryWasteList: (String, Int) -> Unit,
    onNavigateToSuggestedWasteList: (String, Int) -> Unit,
    onNavigateToPopularWasteList: (String, Int) -> Unit,
    onNavigateToDetailWaste: (Int) -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToScan: () -> Unit,
    vm: HomeViewModel = hiltViewModel(),
) {
    val userDataState by vm.user.collectAsState()
    val categoriesState by vm.categories.collectAsState()
    val suggestedWastesState by vm.suggestedWastes.collectAsState()
    val popularWastesState by vm.popularWastes.collectAsState()
    val userId by vm.userId.collectAsState()

    var showBottomSheet by remember { mutableStateOf(false) }
    val state = rememberPullToRefreshState()

    LaunchedEffect(Unit) {
        vm.getUserData()
        vm.getCategories()
        vm.getPopularWaste()
    }
    LaunchedEffect(userDataState) {
        when(userDataState){
            is UiState.Success ->   vm.getSuggestedWaste()
            is UiState.Error -> {
                val error = (userDataState as UiState.Error).error
                if (error is AppError.Network) {
                    showBottomSheet = true
                }
            }
            else -> {}
        }
    }

    LaunchedEffect(categoriesState) {
        if (categoriesState is UiState.Error) {
            val error = (categoriesState as UiState.Error).error
            if (error is AppError.Network) {
                showBottomSheet = true
            }
        }
    }

    LaunchedEffect(suggestedWastesState) {
        if (suggestedWastesState is UiState.Error) {
            val error = (suggestedWastesState as UiState.Error).error
            if (error is AppError.Network) {
                showBottomSheet = true
            }
        }
    }

    LaunchedEffect(popularWastesState) {
        if (popularWastesState is UiState.Error) {
            val error = (popularWastesState as UiState.Error).error
            if (error is AppError.Network) {
                showBottomSheet = true
            }
        }
    }
    Scaffold(
        bottomBar = {
            SafeRecycleBottomNavBar(
                modifier = Modifier.padding(),
                onNavigateToHome = {},
                onNavigateToScan = { onNavigateToScan() },
                onNavigateToProfile = { onNavigateToProfile() },
                activeItem = 0
            )
        }
    ) { innerPadding ->
        PullToRefreshBox(
            modifier = Modifier.padding(innerPadding),
            isRefreshing = categoriesState is UiState.Loading,
            onRefresh = {
                vm.getUserData()
                vm.getCategories()
                vm.getPopularWaste()
            },
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
            LazyColumn(
                verticalArrangement = spacedBy(24.dp),
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            ) {
                item {
                    when (userDataState) {
                        is UiState.Loading -> HeaderSectionSkeleton()
                        is UiState.Success -> {
                            val user = (userDataState as UiState.Success).data
                            Log.d("UserHeader", "$user")
                            HeaderSection(
                                username = user.name,
                                initial = vm.getInitials(user.name),
                                onInitialCardClick = { onNavigateToProfile() }
                            )
                        }

                        is UiState.Error -> { HeaderSectionSkeleton() }

                        else -> {}
                    }
                }
                item {
                    when (userDataState) {
                        is UiState.Loading -> SearchFieldSkeleton()
                        is UiState.Success -> {
                            SearchField(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(13.dp))
                                    .clickable(
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() }
                                    ) { onNavigateToSearch() }
                                    .height(45.dp),
                                value = ""
                            )
                        }

                        is UiState.Error -> {
                            SearchFieldSkeleton()
                        }

                        else -> {}
                    }

                }
                item {
                    when (categoriesState) {
                        is UiState.Loading -> CategorySectionSkeleton()
                        is UiState.Success -> {
                            val categories =
                                (categoriesState as UiState.Success).data
                            CategorySection(
                                categories = categories,
                                onCategoriesClick = { onNavigateToCategory() },
                                onCategoryCardClick = { categoryName, categoryId ->
                                    onNavigateToCategoryWasteList(
                                        categoryName,
                                        categoryId
                                    )
                                },
                            )
                        }

                        is UiState.Error -> { CategorySectionSkeleton() }

                        else -> {}
                    }
                }
                item {
                    when (suggestedWastesState){
                        is UiState.Loading -> SuggestionSectionSkeleton()
                        is UiState.Success->{
                            val suggestedWastes = (suggestedWastesState as UiState.Success).data
                            SuggestedSection(
                                suggestedWaste = suggestedWastes,
                                onWasteCardClick = { onNavigateToDetailWaste(it) },
                                onSuggestedClick = {
                                    onNavigateToSuggestedWasteList("Suggested for You",userId )
                                }
                            )
                        }
                        is UiState.Empty -> {}
                        is UiState.Error -> { SuggestionSectionSkeleton() }
                        else -> {}
                    }
                }
                item {
                    when (popularWastesState){
                        is UiState.Loading ->  PopularSectionSkeleton()
                        is UiState.Success->{
                            val popularWastes = (popularWastesState as UiState.Success).data
                            PopularSection(
                                popularWaste = popularWastes,
                                onWasteCardClick = { onNavigateToDetailWaste(it) },
                                onPopularClick = {
                                    onNavigateToPopularWasteList("Popular Waste",userId)
                                }
                            )
                        }
                        is UiState.Empty -> {}
                        is UiState.Error -> { PopularSectionSkeleton() }
                        else -> {}
                    }
                }
            }
            if (showBottomSheet) {
                LostConnectionBottomSheet(
                    onTryAgainClick = {
                        vm.getUserData()
                        vm.getCategories()
                        vm.getPopularWaste()
                        showBottomSheet = false
                    },
                    onDismissRequest = { showBottomSheet = false }
                )
            }
        }
    }
}