package com.example.saferecycle.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.saferecycle.ui.state.SessionState
import com.example.saferecycle.navigation.nav_graph.Home
import com.example.saferecycle.navigation.nav_graph.Login
import com.example.saferecycle.navigation.nav_graph.authGraph
import com.example.saferecycle.navigation.nav_graph.mainGraph
import com.example.saferecycle.ui.screen.AuthViewModel2
import com.example.saferecycle.ui.screen.splash.SplashScreen
import kotlinx.serialization.Serializable

@Serializable
object Splash

@Composable
fun AppNavigation(
    vm: AuthViewModel2 = hiltViewModel(),
) {
    //token null -> login else home
    val sessionState by vm.sessionState.collectAsState()
//    val routeAfterSplash = if (vm.getToken() == null) Login else Home
    val navController = rememberNavController()

    LaunchedEffect(sessionState) {
        when (sessionState) {
            SessionState.LoggedIn -> {
                navController.navigate(Home) { popUpTo(0) }
            }

            SessionState.LoggedOut -> {
                navController.navigate(Login) { popUpTo(0) }
            }
        }
    }
    NavHost(
        navController = navController,
        startDestination = Splash
    ) {
        composable<Splash> {
            SplashScreen(
                onNavigateToLoginOrHome = {
//                    navController.navigate(routeAfterSplash) {
//                        popUpTo(Splash) { inclusive = true }
//                    }
                },
            )
        }
        mainGraph(navController = navController)
        authGraph(navController = navController)
    }
}