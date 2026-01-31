package com.example.saferecycle.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.saferecycle.ui.theme.SafeRecycleTheme

@Composable
fun InitialCard(initial: String, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .background(
                shape = CircleShape,
                color = SafeRecycleTheme.colors.bright,
            )
            .size(60.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        NormalText(fontSize = 20.sp, text = initial)
    }
}