package com.example.saferecycle.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.IdCard
import com.composables.icons.lucide.KeyRound
import com.composables.icons.lucide.LogOut
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Mail
import com.example.saferecycle.ui.component.InitialCard
import com.example.saferecycle.ui.component.InitialCardSkeleton
import com.example.saferecycle.ui.component.MediumText
import com.example.saferecycle.ui.component.MediumTextSkeleton
import com.example.saferecycle.ui.component.NormalButton
import com.example.saferecycle.ui.component.NormalText
import com.example.saferecycle.ui.component.NormalTextField
import com.example.saferecycle.ui.component.NormalTextSkeleton
import com.example.saferecycle.ui.component.SearchFieldSkeleton
import com.example.saferecycle.ui.component.SecondaryTextSkeleton
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
fun HeaderSectionSkeleton(
) {
    Column(
        verticalArrangement = spacedBy(13.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        InitialCardSkeleton()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = spacedBy(2.dp)
        ) {
            MediumTextSkeleton(modifier = Modifier.fillMaxWidth(0.3f))
            SecondaryTextSkeleton(modifier = Modifier.fillMaxWidth(0.35f))
        }
    }
}

@Composable
fun NameEmailSection(
    username: String,
    email: String,
    onValueChange: (String) -> Unit,
    onValueEmailChange: (String) -> Unit
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
            onValueChange = { onValueEmailChange(it) }
        )
    }
}

@Composable
fun NameEmailSkeleton(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = spacedBy(11.dp)
    ) {
        NormalTextSkeleton(modifier = Modifier.fillMaxWidth(0.2f))
        SearchFieldSkeleton()
        NormalTextSkeleton(modifier = Modifier.fillMaxWidth(0.2f))
        SearchFieldSkeleton()
    }
}

@Composable
fun DisabledButton(
    modifier: Modifier = Modifier,
    text: String,
    isDisabled: Boolean = true,
    color: Color = if (isDisabled) SafeRecycleTheme.colors.stroke else SafeRecycleTheme.colors.accent,
    contentColor: Color = if (isDisabled) SafeRecycleTheme.colors.foreground else SafeRecycleTheme.colors.elementBackground,
    icon: ImageVector? = null,
    contentDescription: String? = null,
    isLoading: Boolean? = null
) {
    val shape = RoundedCornerShape(13.dp)

    Box(
        modifier = modifier
            .background(
                shape = shape,
                color = color
            )
            .clip(shape)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 14.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isLoading == true) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = SafeRecycleTheme.colors.elementBackground
                )
            } else {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = contentDescription,
                        tint = contentColor
                    )
                }
                Spacer(Modifier.width(11.dp))
                MediumText(text = text, color = contentColor)
            }
        }
    }
}

@Composable
fun ButtonsSection(
    onChangePasswordButtonClick: () -> Unit,
    onSignOutButtonClick: () -> Unit,
    isLogoutLoading: Boolean
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
            contentColor = SafeRecycleTheme.colors.foreground,
            isLoading = isLogoutLoading
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