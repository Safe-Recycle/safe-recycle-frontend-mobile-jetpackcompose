package com.example.saferecycle.navigation.nav_graph

import kotlinx.serialization.Serializable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.saferecycle.ui.screen.category.CategoryScreen
import com.example.saferecycle.ui.screen.home.HomeScreen

@Serializable
object Home

@Serializable
object Category


fun NavGraphBuilder.mainGraph(
    navController: NavController,
) {
    composable<Home> {
        HomeScreen(onNavigateToCategory = {
            navController.navigate(Category)
        })
    }
    composable<Category> {
        CategoryScreen(onBackClick = {navController.navigateUp()})
    }
}
