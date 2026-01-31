package com.example.saferecycle.ui.component

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.saferecycle.ui.theme.SafeRecycleTheme

@Composable
fun HorizontalLine(thickness: Dp = 1.dp, color: Color = SafeRecycleTheme.colors.stroke) {
    HorizontalDivider(
        thickness = thickness,
        color = color
    )
}