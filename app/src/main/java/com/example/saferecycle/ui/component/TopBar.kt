package com.example.saferecycle.ui.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.composables.icons.lucide.ChevronLeft
import com.composables.icons.lucide.Lucide
import com.example.saferecycle.ui.theme.SafeRecycleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    text: String,
    onBackClick: () -> Unit,
    containerColors: Color = SafeRecycleTheme.colors.background,
    navigationIconContentColor: Color = SafeRecycleTheme.colors.foreground,
    titleContentColor: Color = SafeRecycleTheme.colors.foreground
) {
    CenterAlignedTopAppBar(
        title = { MediumText(text = text, color = titleContentColor) },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Lucide.ChevronLeft,
                    contentDescription = "Back Icon"
                )
            }
        },
        colors = TopAppBarColors(
            containerColor = containerColors,
            scrolledContainerColor = SafeRecycleTheme.colors.background,
            navigationIconContentColor = navigationIconContentColor,
            titleContentColor = titleContentColor,
            actionIconContentColor = SafeRecycleTheme.colors.foreground
        )
    )
}