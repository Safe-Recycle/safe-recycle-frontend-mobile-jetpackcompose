package com.example.saferecycle.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.saferecycle.ui.theme.SafeRecycleTheme

@Composable
fun InitialCardSkeleton(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .background(
                shape = CircleShape,
                color = SafeRecycleTheme.colors.stroke,
            )
            .size(60.dp)

    )
}