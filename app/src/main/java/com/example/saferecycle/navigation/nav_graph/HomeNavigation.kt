package com.example.saferecycle.navigation.nav_graph

import androidx.navigation.NavBackStackEntry
import kotlinx.serialization.Serializable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.saferecycle.ui.screen.category.CategoryScreen
import com.example.saferecycle.ui.screen.home.HomeScreen
import com.example.saferecycle.ui.screen.waste_list.WasteListScreen

@Serializable
object Home

@Serializable
object Category

@Serializable
data class WasteList(
    val source: String,
    val categoryId: Int? = null
)

fun NavGraphBuilder.mainGraph(
    navController: NavController,
) {
    composable<Home> {
        HomeScreen(
            onNavigateToCategory = {
                navController.navigate(Category)
            },
            onNavigateToSearch = {},
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
            onNavigateToDetailWaste = {}
        )
    }
    composable<Category> {from: NavBackStackEntry ->
        CategoryScreen(onNavigateToCategoryWasteList = { source, categoryId ->
            navController.navigate(
                WasteList(
                    source = source,
                    categoryId = categoryId
                )
            )
        },onBackClick = { navController.navigateUp() })
    }
    composable<WasteList> { backStackEntry ->
        val wasteList = backStackEntry.toRoute<WasteList>()
        WasteListScreen(
            source = wasteList.source,
            categoryId = wasteList.categoryId,
            onBackClick = { navController.navigateUp() },
            onNavigateToDetailWaste = {}
        )
    }
}
