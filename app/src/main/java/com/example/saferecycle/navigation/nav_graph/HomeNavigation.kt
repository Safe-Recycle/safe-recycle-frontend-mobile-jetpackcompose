package com.example.saferecycle.navigation.nav_graph

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry
import kotlinx.serialization.Serializable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.saferecycle.ui.screen.category.CategoryScreen
import com.example.saferecycle.ui.screen.home.HomeScreen
import com.example.saferecycle.ui.screen.profile.ProfileScreen
import com.example.saferecycle.ui.screen.scan_waste.ScanWasteScreen
import com.example.saferecycle.ui.screen.search.SearchScreen
import com.example.saferecycle.ui.screen.waste_details.WasteDetailsScreen
import com.example.saferecycle.ui.screen.waste_list.WasteListScreen

@Serializable
object Home

@Serializable
object Category

@Serializable
data class WasteList(
    val source: String,
    val categoryId: Int? = null
) {
    companion object {
        fun from(savedStateHandle: SavedStateHandle) =
            savedStateHandle.toRoute<WasteList>()
    }
}

@Serializable
data class WasteDetails(
    val wasteId: Int
) {
    companion object {
        fun from(savedStateHandle: SavedStateHandle) =
            savedStateHandle.toRoute<WasteDetails>()
    }
}

@Serializable
object Search

@Serializable
object Profile

@Serializable
object ScanWaste

fun NavGraphBuilder.mainGraph(
    navController: NavController,
) {
    composable<Home> {
        HomeScreen(
            onNavigateToCategory = {
                navController.navigate(Category)
            },
            onNavigateToSearch = { navController.navigate(Search) },
            onNavigateToCategoryWasteList = { source, categoryId ->
                navController.navigate(
                    WasteList(
                        source = source,
                        categoryId = categoryId
                    )
                )
            },
            onNavigateToSuggestedWasteList = { source ->
                navController.navigate(WasteList(source = source))
            },
            onNavigateToPopularWasteList = { source ->
                navController.navigate(WasteList(source = source))
            },
            onNavigateToDetailWaste = { wasteId ->
                navController.navigate(WasteDetails(wasteId = wasteId))
            },
            onNavigateToProfile = {
                navController.navigate(Profile)
            },
            onNavigateToScan = { navController.navigate(ScanWaste) },
        )
    }
    composable<Category> { from: NavBackStackEntry ->
        CategoryScreen(onNavigateToCategoryWasteList = { source, categoryId ->
            navController.navigate(
                WasteList(
                    source = source,
                    categoryId = categoryId
                )
            )
        }, onBackClick = { navController.navigateUp() })
    }
    composable<WasteList> { backStackEntry ->
        val wasteList = backStackEntry.toRoute<WasteList>()
        WasteListScreen(
            source = wasteList.source,
            categoryId = wasteList.categoryId,
            onBackClick = { navController.navigateUp() },
            onNavigateToDetailWaste = { wasteId ->
                navController.navigate(WasteDetails(wasteId = wasteId))
            }
        )
    }

    composable<WasteDetails> { backStackEntry ->
        val wasteDetails = backStackEntry.toRoute<WasteDetails>()
        WasteDetailsScreen(
            wasteId = wasteDetails.wasteId,
            onBackClick = { navController.navigateUp() }
        )
    }
    composable<Search> {
        SearchScreen(
            onBackClick = { navController.navigateUp() },
            onNavigateToDetailWaste = { wasteId ->
                navController.navigate(WasteDetails(wasteId = wasteId))
            }
        )
    }
    composable<Profile> {
        ProfileScreen(
            onNavigateToChangePassword = { userId->
                navController.navigate(
                    ChangePassword(
                        userId = userId
                    )
                )
            },
            onNavigateToLogin = {
                navController.navigate(Login) {
                    popUpTo(Home) {
                        inclusive = true
                    }
                }
            },
            onNavigateToScan = { navController.navigate(ScanWaste) },
            onNavigateToHome = {
                navController.navigate(Home) {
                    popUpTo(Profile) {
                        inclusive = true
                    }
                }
            }
        )
    }
    composable<ScanWaste> {
        ScanWasteScreen(onBackClick = { navController.navigateUp() })
    }
}
