package com.example.saferecycle.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.House
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.ScanSearch
import com.composables.icons.lucide.UserRound
import com.example.saferecycle.ui.theme.SafeRecycleTheme

@Composable
fun SafeRecycleBottomNavBar(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit,
    onNavigateToScan: () -> Unit,
    onNavigateToProfile: () -> Unit,
    activeItem: Int
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CommonNavItem(
            modifier = Modifier.weight(1f),
            isActive = activeItem == 0,
            iconImageVector = Lucide.House,
            iconContentDescription = "Icon for Home Screen",
            onClick = { onNavigateToHome() }
        )
        ScanNavItem(onClick = { onNavigateToScan() })
        CommonNavItem(
            modifier = Modifier.weight(1f),
            isActive = activeItem == 2,
            iconImageVector = Lucide.UserRound,
            iconContentDescription = "Icon for Profile Screen",
            onClick = { onNavigateToProfile() }
        )
    }
}

@Composable
fun CommonNavItem(
    modifier: Modifier = Modifier,
    isActive: Boolean,
    iconImageVector: ImageVector,
    iconContentDescription: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() }
            .padding(vertical = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = spacedBy(4.dp)
    ) {
        Icon(
            imageVector = iconImageVector,
            contentDescription = iconContentDescription,
            Modifier.size(29.dp),
            tint = SafeRecycleTheme.colors.textSecondary
        )
        Box(
            modifier = Modifier
                .background(
                    color = if (isActive) SafeRecycleTheme.colors.accent else Color.Transparent,
                    shape = CircleShape
                )
                .size(5.dp)
                .padding(bottom = 4.dp)
        )

    }
}

@Composable
fun ScanNavItem(modifier: Modifier = Modifier, onClick: () -> Unit) {
    val shape = RoundedCornerShape(17.dp)
    Box(
        modifier = Modifier
            .background(
                color = SafeRecycleTheme.colors.accent,
                shape = shape
            )
            .clip(shape)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() }
            .padding(14.dp)
    ) {
        Icon(
            modifier = Modifier.size(29.dp),
            imageVector = Lucide.ScanSearch,
            contentDescription = "Icon for Scan Waste",
            tint = SafeRecycleTheme.colors.elementBackground
        )
    }
}