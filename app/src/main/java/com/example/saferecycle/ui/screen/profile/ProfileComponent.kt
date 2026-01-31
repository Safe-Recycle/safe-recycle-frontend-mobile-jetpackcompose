package com.example.saferecycle.ui.screen.profile

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.IdCard
import com.composables.icons.lucide.KeyRound
import com.composables.icons.lucide.LogOut
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Mail
import com.example.saferecycle.ui.component.InitialCard
import com.example.saferecycle.ui.component.MediumText
import com.example.saferecycle.ui.component.NormalButton
import com.example.saferecycle.ui.component.NormalText
import com.example.saferecycle.ui.component.NormalTextField
import com.example.saferecycle.ui.theme.SafeRecycleTheme

@Composable
fun HeaderSection(
    username: String,
    initial: String,
    email: String
) {
    Column(
        verticalArrangement = spacedBy(13.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        InitialCard(initial)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MediumText(text = username)
            NormalText(
                text = email,
                color = SafeRecycleTheme.colors.textSecondary
            )
        }
    }
}

@Composable
fun NameEmailSection(
    username: String,
    email: String,
    onValueChange: (String) -> Unit
) {
    Column(
        verticalArrangement = spacedBy(11.dp)
    ) {
        NormalText(text = "Name")
        NormalTextField(
            placeholder = "Update Your Name",
            isPassword = false,
            leadingIcon = Lucide.IdCard,
            leadingIconContentDescription = "Icon for edit username",
            value = username,
            onValueChange = { onValueChange(it) }
        )
        NormalText(text = "Email")
        NormalTextField(
            placeholder = "This is Your Email",
            isPassword = false,
            leadingIcon = Lucide.Mail,
            leadingIconContentDescription = "Icon for email",
            value = email,
        )
    }
}

@Composable
fun ButtonsSection(
    onChangePasswordButtonClick: () -> Unit,
    onSignOutButtonClick: () -> Unit
) {
    Column(verticalArrangement = spacedBy(17.dp)) {
        NormalButton(
            onClick = { onChangePasswordButtonClick() },
            text = "Change Password",
            icon = Lucide.KeyRound,
            contentDescription = "Icon for Change Password"
        )
        NormalButton(
            onClick = { onSignOutButtonClick() },
            text = "Sign Out",
            icon = Lucide.LogOut,
            contentDescription = "Icon for Sign Out",
            color = SafeRecycleTheme.colors.stroke,
            contentColor = SafeRecycleTheme.colors.foreground
        )
    }
}

@Composable
fun AppVersion(modifier: Modifier = Modifier) {
    NormalText(
        text = "App Version 1.0",
        color = Color(0xFFB7B7B7)
    )
}