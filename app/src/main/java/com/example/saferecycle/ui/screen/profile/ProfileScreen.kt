package com.example.saferecycle.ui.screen.profile

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.saferecycle.ui.component.HorizontalLine

@Composable
fun ProfileScreen(
    onNavigateToChangePassword: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val staticUsername = "Elma"
    var username by remember { mutableStateOf("Elma") }
    val email = "elma@gmail.com"
    Scaffold() { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                HeaderSection(username = staticUsername, initial = "E", email = email)
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
                    onSignOutButtonClick = { onNavigateToLogin() }
                )
            }
            item {
                AppVersion()
            }
        }
    }
}