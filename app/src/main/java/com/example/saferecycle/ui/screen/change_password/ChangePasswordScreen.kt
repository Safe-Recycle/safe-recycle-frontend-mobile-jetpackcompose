package com.example.saferecycle.ui.screen.change_password

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.composables.icons.lucide.KeyRound
import com.composables.icons.lucide.Lock
import com.composables.icons.lucide.Lucide
import com.example.saferecycle.data.model.ChangeUserPassword
import com.example.saferecycle.ui.component.ErrorField
import com.example.saferecycle.ui.component.NormalButton
import com.example.saferecycle.ui.component.NormalText
import com.example.saferecycle.ui.component.NormalTextField
import com.example.saferecycle.ui.component.TopBar
import com.example.saferecycle.ui.state.AppError
import com.example.saferecycle.ui.state.UiState

@Composable
fun ChangePasswordScreen(
    onBackClick: () -> Unit,
    onNavigateToLogin: () -> Unit,
    vm: ChangePasswordViewModel = hiltViewModel()
) {
    var oldPassword by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confPassword by remember { mutableStateOf("") }
    val updateUserPasswordState by vm.updateUserPassword.collectAsState()
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(updateUserPasswordState) {
        when (updateUserPasswordState) {
            is UiState.Success<*> -> {
                val updatePasswordMessage =
                    (updateUserPasswordState as UiState.Success<String>).data
                Toast.makeText(
                    context,
                    updatePasswordMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }

            is UiState.Error -> {
                errorMessage =
                    when (val errorState =
                        (updateUserPasswordState as UiState.Error).error) {
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

    Scaffold(
        topBar = {
            TopBar(
                onBackClick = { onBackClick() },
                text = "Change Password"
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = spacedBy(40.dp)
        ) {
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(11.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(11.dp),
                    ) {
                        NormalText(text = "Old Password")
                        NormalTextField(
                            placeholder = "Enter your old password",
                            value = oldPassword,
                            onValueChange = { oldPassword = it },
                            isPassword = true,
                            leadingIcon = Lucide.Lock,
                            leadingIconContentDescription = "Icon for Old Password"
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(11.dp),
                    ) {
                        NormalText(text = "Password")
                        NormalTextField(
                            placeholder = "Enter your new password",
                            value = password,
                            onValueChange = { password = it },
                            isPassword = true,
                            leadingIcon = Lucide.KeyRound,
                            leadingIconContentDescription = "Icon for New Password"
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(11.dp),
                    ) {
                        NormalText(text = "Confirm Password")
                        NormalTextField(
                            placeholder = "Re-enter your new password",
                            value = confPassword,
                            onValueChange = { confPassword = it },
                            isPassword = true,
                            leadingIcon = Lucide.KeyRound,
                            leadingIconContentDescription = "Icon for Re-enter New Password"
                        )
                    }
                    ErrorField(
                        errorMessage = errorMessage,
                        isVisible = updateUserPasswordState is UiState.Error
                    )
                }
            }
            item {
                NormalButton(
                    onClick = {
                        vm.changeUserPassword(
                            ChangeUserPassword(
                                oldPassword = oldPassword,
                                newPassword = password,
                                confirmPassword = confPassword
                            )
                        )
                    },
                    text = "Change Password",
                    isLoading = updateUserPasswordState is UiState.Loading
                )
            }
        }
    }
}