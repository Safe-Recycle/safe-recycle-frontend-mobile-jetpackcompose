package com.example.saferecycle.ui.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import com.composables.icons.lucide.ChevronLeft
import com.composables.icons.lucide.Lucide
import com.example.saferecycle.ui.theme.SafeRecycleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(text: String, onBackClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { MediumText(text = text) },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Lucide.ChevronLeft,
                    contentDescription = "Back Icon"
                )
            }
        },
        colors = TopAppBarColors(
            containerColor = SafeRecycleTheme.colors.background,
            scrolledContainerColor = SafeRecycleTheme.colors.background,
            navigationIconContentColor = SafeRecycleTheme.colors.foreground,
            titleContentColor = SafeRecycleTheme.colors.foreground,
            actionIconContentColor = SafeRecycleTheme.colors.foreground
        )
    )
}