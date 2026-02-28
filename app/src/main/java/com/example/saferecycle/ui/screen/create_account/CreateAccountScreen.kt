package com.example.saferecycle.ui.screen.create_account

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement.spacedBy
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.composables.icons.lucide.IdCard
import com.composables.icons.lucide.KeyRound
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Mail
import com.example.saferecycle.ui.state.AppError
import com.example.saferecycle.ui.state.UiState
import com.example.saferecycle.ui.component.BoldedText
import com.example.saferecycle.ui.component.ErrorField
import com.example.saferecycle.ui.component.NormalButton
import com.example.saferecycle.ui.component.NormalText
import com.example.saferecycle.ui.component.NormalTextField
import com.example.saferecycle.ui.component.TopBar
import com.example.saferecycle.ui.screen.AuthViewModel2

@Composable
fun CreateAccountScreen(
    onBackClick: () -> Unit,
    vm: AuthViewModel2 = hiltViewModel()
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current
    val registerState by vm.registerState.collectAsState()

    LaunchedEffect(registerState) {
        when (registerState) {
            is UiState.Success -> {
                val registerMessage =
                    (registerState as UiState.Success<String>).data
                Toast.makeText(
                    context,
                    registerMessage,
                    Toast.LENGTH_SHORT
                ).show()
                onBackClick()
            }

            is UiState.Error -> {
                errorMessage =
                    when (val errorState =
                        (registerState as UiState.Error).error) {
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
                text = ""
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            verticalArrangement = spacedBy(39.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { BoldedText(text = " Create Account") }
            item {
                Column(
                    verticalArrangement = spacedBy(13.dp)
                ) {
                    Column(
                        verticalArrangement = spacedBy(9.dp),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        NormalText(text = "Name")
                        NormalTextField(
                            placeholder = "Enter your name",
                            value = username,
                            onValueChange = { username = it },
                            isPassword = false,
                            leadingIcon = Lucide.IdCard,
                            leadingIconContentDescription = "Icon for Username"
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = spacedBy(9.dp),
                    ) {
                        NormalText(text = "Email")
                        NormalTextField(
                            placeholder = "Enter your email",
                            value = email,
                            onValueChange = { email = it },
                            isPassword = false,
                            leadingIcon = Lucide.Mail,
                            leadingIconContentDescription = "Icon for Email"
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = spacedBy(9.dp),
                    ) {
                        NormalText(text = "Password")
                        NormalTextField(
                            placeholder = "Enter your password",
                            value = password,
                            onValueChange = { password = it },
                            isPassword = true,
                            leadingIcon = Lucide.KeyRound,
                            leadingIconContentDescription = "Icon for Password"
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = spacedBy(9.dp),
                    ) {
                        NormalText(text = "Confirm Password")
                        NormalTextField(
                            placeholder = "Re-enter your password",
                            value = repeatPassword,
                            onValueChange = { repeatPassword = it },
                            isPassword = true,
                            leadingIcon = Lucide.KeyRound,
                            leadingIconContentDescription = "Icon for Re-enter Password"
                        )
                    }
                }
            }
            item {
                ErrorField(
                    errorMessage = errorMessage,
                    isVisible = registerState is UiState.Error
                )
            }
            item {
                NormalButton(
                    onClick = {
                        vm.register(
                            email = email,
                            name = username,
                            password = password,
                            repeatPassword = repeatPassword
                        )
                    },
                    text = "Sign Up",
                    isLoading = registerState is UiState.Loading
                )
            }
        }
    }
}