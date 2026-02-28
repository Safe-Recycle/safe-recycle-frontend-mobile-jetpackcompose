package com.example.saferecycle.navigation.nav_graph

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.saferecycle.ui.screen.change_password.ChangePasswordScreen
import com.example.saferecycle.ui.screen.create_account.CreateAccountScreen
import com.example.saferecycle.ui.screen.login.LoginScreen
import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object CreateAccount

@Serializable
data class ChangePassword(
    val userId:Int
){
    companion object {
        fun from(savedStateHandle: SavedStateHandle) =
            savedStateHandle.toRoute<ChangePassword>()
    }
}

fun NavGraphBuilder.authGraph(
    navController: NavController,
) {
    composable<Login> {
        LoginScreen(
            onNavigateToCreateAccount = { navController.navigate(CreateAccount) }
        )
    }
    composable<CreateAccount> {
        CreateAccountScreen(
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