package com.example.saferecycle.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.composables.icons.lucide.Lock
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Mail
import com.example.saferecycle.R
import com.example.saferecycle.ui.state.AppError
import com.example.saferecycle.ui.state.UiState
import com.example.saferecycle.ui.component.BoldedText
import com.example.saferecycle.ui.component.ErrorField
import com.example.saferecycle.ui.component.NormalButton
import com.example.saferecycle.ui.component.NormalText
import com.example.saferecycle.ui.component.NormalTextField
import com.example.saferecycle.ui.screen.AuthViewModel2
import com.example.saferecycle.ui.theme.SafeRecycleTheme

@Composable
fun LoginScreen(
    onNavigateToCreateAccount: () -> Unit,
    vm: AuthViewModel2 = hiltViewModel()
) {
    val loginState by vm.loginState.collectAsState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(loginState) {
        if (loginState is UiState.Error) {
            errorMessage =
                when (val errorState = (loginState as UiState.Error).error) {
                    is AppError.Network -> errorState.message
                    is AppError.Server -> errorState.message
                    is AppError.Unknown -> errorState.message
                    is AppError.Forbidden -> errorState.message
                    is AppError.NotFound -> errorState.message
                    is AppError.Unauthorized -> errorState.message
                    is AppError.Format -> errorState.message
                }
        }
    }

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 24.dp),
        verticalArrangement = spacedBy(39.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(54.dp))
        }
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.app_logo),
                    contentDescription = "Safe&Recycle Logo",
                    Modifier.size(73.dp)
                )
                BoldedText(text = "Sign In to your account")
            }
        }
        item {
            Column(verticalArrangement = spacedBy(13.dp)) {
                Column(verticalArrangement = spacedBy(9.dp)) {
                    NormalText(text = "Email")
                    NormalTextField(
                        value = email,
                        onValueChange = { email = it },
                        isPassword = false,
                        leadingIcon = Lucide.Mail,
                        leadingIconContentDescription = "Icon for Email",
                        placeholder = "Enter your email",
                    )
                }
                Column(verticalArrangement = spacedBy(9.dp)) {
                    NormalText(text = "Password")
                    NormalTextField(
                        value = password,
                        onValueChange = { password = it },
                        isPassword = true,
                        leadingIcon = Lucide.Lock,
                        leadingIconContentDescription = "Icon for Password",
                        placeholder = "Enter your password",
                    )
                }
            }
        }
        item {
            Column(verticalArrangement = spacedBy(17.dp)) {
                ErrorField(
                    errorMessage = errorMessage,
                    isVisible = loginState is UiState.Error
                )
                NormalButton(
                    onClick = {
                        vm.login(email, password)
                    },
                    text = "Sign In",
                    isLoading = loginState is UiState.Loading
                )
                NormalButton(
                    onClick = { onNavigateToCreateAccount() },
                    text = "Create Account",
                    color = SafeRecycleTheme.colors.background,
                    contentColor = SafeRecycleTheme.colors.accent
                )
            }
        }
    }
}