package com.example.saferecycle.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.saferecycle.ui.screen.change_password.ChangePasswordScreen
import com.example.saferecycle.ui.screen.create_account.CreateAccountScreen
import com.example.saferecycle.ui.screen.login.LoginScreen
import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object CreateAccount

@Serializable
object ChangePassword

fun NavGraphBuilder.authGraph(
    navController: NavController,
) {
    composable<Login> {
        LoginScreen(
            onNavigateToHome = {
                navController.navigate(Home) {
                    popUpTo(Login) {
                        inclusive = true
                    }
                }
            },
            onNavigateToCreateAccount = { navController.navigate(CreateAccount) }
        )
    }
    composable<CreateAccount> {
        CreateAccountScreen(
            onNavigateToHome = {
                navController.navigate(Home) {
                    popUpTo(Login) {
                        inclusive = true
                    }
                }
            },
            onBackClick = { navController.navigateUp() })
    }
    composable<ChangePassword> {
        ChangePasswordScreen(
            onBackClick = { navController.navigateUp() },
            onNavigateToLogin = {
                navController.navigate(Login) {
                    popUpTo(Home) {
                        inclusive = true
                    }
                }
            })
    }
}