package com.example.saferecycle.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.saferecycle.data.dummyWastes
import com.example.saferecycle.ui.component.SafeRecycleBottomNavBar
import com.example.saferecycle.ui.component.SearchField
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.saferecycle.data.network.Resource
import com.example.saferecycle.ui.component.SearchFieldSkeleton

@Composable
fun HomeScreen(
    onNavigateToSearch: () -> Unit,
    onNavigateToCategory: () -> Unit,
    onNavigateToCategoryWasteList: (String, Int) -> Unit,
    onNavigateToSuggestedWasteList: (String) -> Unit,
    onNavigateToPopularWasteList: (String) -> Unit,
    onNavigateToDetailWaste: (Int) -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToScan: () -> Unit,
    vm: HomeViewModel = hiltViewModel(),
) {
    val userDataState by vm.user.collectAsState()
    val categoriesState by vm.categories.collectAsState()
    val suggestedWastesState by vm.suggestedWastes.collectAsState()
    val popularWastesState by vm.popularWastes.collectAsState()
    LaunchedEffect(Unit) {
        vm.getUserData()
        vm.getDummyCategories()
        vm.getDummySuggestedWastes()
        vm.getDummyPopularWastes()
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
        LazyColumn(
            verticalArrangement = spacedBy(24.dp),
            modifier = Modifier
                .padding(innerPadding)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ) {
            item {
                when (userDataState) {
                    is Resource.Loading -> HeaderSectionSkeleton()
                    is Resource.Success -> {
                        val user = (userDataState as Resource.Success).data
                        HeaderSection(
                            username = user.name,
                            initial = "E",
                            onInitialCardClick = { onNavigateToProfile() })
                    }

                    else -> {}
                }
            }
            item {
                when (userDataState) {
                    is Resource.Loading -> SearchFieldSkeleton()
                    is Resource.Success -> {
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
                    else -> {}
                }

            }
            item {
                when (categoriesState) {
                    is Resource.Loading -> CategorySectionSkeleton()
                    is Resource.Success -> {
                        val categories =
                            (categoriesState as Resource.Success).data
                        CategorySection(
                            categories = categories,
                            onCategoriesClick = { onNavigateToCategory() },
                            onCategoryCardClick = { categoryName, categoryId ->
                                onNavigateToCategoryWasteList(
                                    "$categoryName Category",
                                    categoryId
                                )
                            },
                        )
                    }

                    else -> {}
                }
            }
            item {
                when (suggestedWastesState) {
                    is Resource.Loading -> SuggestionSectionSkeleton()
                    is Resource.Success -> {
                        val suggestedWastes =
                            (suggestedWastesState as Resource.Success).data
                        SuggestedSection(
                            suggestedWaste = suggestedWastes,
                            onWasteCardClick = { onNavigateToDetailWaste(it) },
                            onSuggestedClick = {
                                onNavigateToSuggestedWasteList("Suggested for You")
                            }
                        )
                    }

                    else -> {}
                }
            }
            item {
                when (popularWastesState) {
                    is Resource.Loading -> PopularSectionSkeleton()
                    is Resource.Success -> {
                        val popularWastes =
                            (popularWastesState as Resource.Success).data
                        PopularSection(
                            popularWaste = popularWastes,
                            onWasteCardClick = { onNavigateToDetailWaste(it) },
                            onPopularClick = {
                                onNavigateToPopularWasteList("Popular Waste")
                            }
                        )
                    }
                    else -> {}
                }
            }
        }
    }
}