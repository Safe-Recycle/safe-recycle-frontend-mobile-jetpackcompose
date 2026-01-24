package com.example.saferecycle.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.saferecycle.navigation.nav_graph.Home
import com.example.saferecycle.navigation.nav_graph.Login
import com.example.saferecycle.navigation.nav_graph.mainGraph
import com.example.saferecycle.ui.screen.splash.SplashScreen
import kotlinx.serialization.Serializable

@Serializable
object Splash

@Composable
fun AppNavigation(
) {
    //token null -> login else home
    val routeAfterSplash = if (false/*authViewModel.getToken() == null*/) Login else Home
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Splash
    ) {
        composable<Splash> {
            SplashScreen(
                onNavigateToLoginOrHome = {
                    navController.navigate(routeAfterSplash) {
                        popUpTo(Splash) { inclusive = true }
                    }
                },
            )
        }
        mainGraph(navController = navController)
    }
}