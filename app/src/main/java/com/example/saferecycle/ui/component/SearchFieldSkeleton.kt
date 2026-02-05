package com.example.saferecycle.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.saferecycle.ui.theme.SafeRecycleTheme

@Composable
fun SearchFieldSkeleton(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .background(
                shape = RoundedCornerShape(13.dp),
                color = SafeRecycleTheme.colors.stroke
            )
            .height(45.dp)
            .fillMaxWidth()
    )
}