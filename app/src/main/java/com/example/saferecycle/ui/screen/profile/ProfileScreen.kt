package com.example.saferecycle.ui.screen.profile

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.saferecycle.ui.state.AppError
import com.example.saferecycle.data.network.Resource
import com.example.saferecycle.ui.state.UiState
import com.example.saferecycle.ui.component.HorizontalLine
import com.example.saferecycle.ui.component.SafeRecycleBottomNavBar
import com.example.saferecycle.ui.screen.AuthViewModel2
import kotlin.math.log

@Composable
fun ProfileScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToScan: () -> Unit,
    onNavigateToChangePassword: () -> Unit,
    onNavigateToLogin: () -> Unit,
    vm: AuthViewModel2 = hiltViewModel()
) {
    val staticUsername = "Elma"
    var username by remember { mutableStateOf("Elma") }
    val email = "elma@gmail.com"
    val logoutState by vm.logoutState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(logoutState) {
        when (logoutState) {
            is UiState.Success<*> -> {
                val logoutMessage = (logoutState as UiState.Success).data
                Toast.makeText(
                    context,
                    logoutMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }

            is UiState.Error -> {
                val logoutMessage = when (val errorState =
                    (logoutState as UiState.Error).error) {
                    is AppError.Network -> errorState.message
                    is AppError.Server -> errorState.message
                    is AppError.Unknown -> errorState.message
                    is AppError.Forbidden -> errorState.message
                    is AppError.NotFound -> errorState.message
                    is AppError.Unauthorized -> errorState.message
                    is AppError.Format -> errorState.message
                }
                Toast.makeText(
                    context,
                    logoutMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> {}
        }
    }

    Scaffold(
        bottomBar = {
            SafeRecycleBottomNavBar(
                onNavigateToHome = { onNavigateToHome() },
                onNavigateToScan = { onNavigateToScan() },
                onNavigateToProfile = { },
                activeItem = 2
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                HeaderSection(
                    username = staticUsername,
                    initial = "E",
                    email = email
                )
            }
            item {
                NameEmailSection(
                    username = username,
                    email = email,
                    onValueChange = { username = it }
                )
            }
            item {
                HorizontalLine()

            }
            item {
                ButtonsSection(
                    onChangePasswordButtonClick = { onNavigateToChangePassword() },
                    onSignOutButtonClick = { vm.logout() },
                    isLogoutLoading = logoutState is UiState.Loading
                )
            }
            item {
                AppVersion()
            }
        }
    }
}