package com.example.saferecycle.ui.screen.change_password

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.IdCard
import com.composables.icons.lucide.KeyRound
import com.composables.icons.lucide.Lock
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Mail
import com.example.saferecycle.ui.component.NormalButton
import com.example.saferecycle.ui.component.NormalText
import com.example.saferecycle.ui.component.NormalTextField
import com.example.saferecycle.ui.component.TopBar

@Composable
fun ChangePasswordScreen(
    onBackClick: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var oldPassword by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confPassword by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopBar(
                onBackClick = { onBackClick() },
                text = "Change Password"
            )
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier
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
                }
            }
            item{
                NormalButton(
                    onClick = { onNavigateToLogin() },
                    text = "Change Password"
                )
            }
        }
    }
}