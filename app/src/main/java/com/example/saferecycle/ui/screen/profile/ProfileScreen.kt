package com.example.saferecycle.ui.screen.profile

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
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
import com.example.saferecycle.data.model.User
import com.example.saferecycle.ui.component.ErrorField
import com.example.saferecycle.ui.state.AppError
import com.example.saferecycle.ui.state.UiState
import com.example.saferecycle.ui.component.HorizontalLine
import com.example.saferecycle.ui.component.SafeRecycleBottomNavBar
import com.example.saferecycle.ui.screen.AuthViewModel2

@Composable
fun ProfileScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToScan: () -> Unit,
    onNavigateToChangePassword: (Int) -> Unit,
    authViewModel2: AuthViewModel2 = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    var usernameBackend by remember { mutableStateOf("") }
    var emailBackend by remember { mutableStateOf("") }
    var isEdited by remember { mutableStateOf(false) }
    var isNameEdited by remember { mutableStateOf(false) }
    var isEmailEdited by remember { mutableStateOf(false) }
    val logoutState by authViewModel2.logoutState.collectAsState()
    val userState by profileViewModel.user.collectAsState()
    val userId by profileViewModel.userId.collectAsState()
    val updateUserDataState by profileViewModel.updateUserData.collectAsState()
    var updateUserDataErrorMessage by remember { mutableStateOf("") }
    val request = hashMapOf<String, String>()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        profileViewModel.getUserData()
    }

    LaunchedEffect(usernameBackend, emailBackend) {
        if (userState is UiState.Success) {
            val user = (userState as UiState.Success<User>).data
            isNameEdited = usernameBackend != user.name
            isEmailEdited = emailBackend != user.email
            isEdited = isNameEdited || isEmailEdited
        }
    }

    LaunchedEffect(updateUserDataState) {
        when (updateUserDataState) {
            is UiState.Success -> {
                profileViewModel.getUserData()
                isEdited = false
            }

            is UiState.Error -> {

                updateUserDataErrorMessage =
                    when (val errorState =
                        (updateUserDataState as UiState.Error).error) {
                        is AppError.Network -> errorState.message
                        is AppError.Server -> errorState.message
                        is AppError.Unknown -> errorState.message
                        is AppError.Forbidden -> errorState.message
                        is AppError.NotFound -> errorState.message
                        is AppError.Unauthorized -> errorState.message
                        is AppError.Format -> errorState.message
                    }
            }

            else -> {}
        }
    }

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
                when (userState) {
                    is UiState.Loading -> HeaderSectionSkeleton()
                    is UiState.Success -> {
                        val user = (userState as UiState.Success).data
                        HeaderSection(
                            username = user.name,
                            initial = profileViewModel.getInitials(user.name),
                            email = user.email
                        )
                    }

                    is UiState.Error -> {
                        HeaderSectionSkeleton()
                    }

                    else -> {}
                }
            }
            item {
                when (userState) {
                    is UiState.Loading -> NameEmailSkeleton()
                    is UiState.Success -> {
                        val user = (userState as UiState.Success).data
                        LaunchedEffect(user) {
                            usernameBackend = user.name
                            emailBackend = user.email
                        }
                        NameEmailSection(
                            username = usernameBackend,
                            email = emailBackend,
                            onValueChange = { usernameBackend = it },
                            onValueEmailChange = { emailBackend = it }
                        )
                    }

                    is UiState.Error -> {
                        NameEmailSkeleton()
                    }

                    else -> {}
                }
            }
            item {
                ErrorField(
                    errorMessage = updateUserDataErrorMessage,
                    isVisible = updateUserDataState is UiState.Error
                )
            }
            item {
                if (isEdited) {
                    DisabledButton(
                        text = "Save Changes", isDisabled = false,
                        isLoading = userState is UiState.Loading,
                        modifier = Modifier.clickable {
                            if (isNameEdited) {
                                request["name"] = usernameBackend
                            }
                            if (isEmailEdited) {
                                request["email"] = emailBackend
                            }
                            profileViewModel.updateUserData(
                                request = request
                            )
                        }
                    )
                } else {
                    DisabledButton(text = "No Change Have Been Made")
                }
            }
            item {
                HorizontalLine()
            }
            item {
                ButtonsSection(
                    onChangePasswordButtonClick = { onNavigateToChangePassword(userId) },
                    onSignOutButtonClick = { authViewModel2.logout() },
                    isLogoutLoading = logoutState is UiState.Loading
                )
            }
            item {
                AppVersion()
            }
        }
    }
}