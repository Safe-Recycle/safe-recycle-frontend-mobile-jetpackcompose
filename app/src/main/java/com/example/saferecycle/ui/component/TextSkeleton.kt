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
fun NormalTextSkeleton(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(
                shape = RoundedCornerShape(13.dp),
                color = SafeRecycleTheme.colors.stroke
            )
            .height(18.dp)
            .fillMaxWidth()

    )
}

@Composable
fun MediumTextSkeleton(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(
                shape = RoundedCornerShape(13.dp),
                color = SafeRecycleTheme.colors.stroke
            )
            .height(21.dp)
            .fillMaxWidth()

    )
}

@Composable
fun SecondaryTextSkeleton(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(13.dp),
                color = SafeRecycleTheme.colors.stroke
            )
            .height(16.dp)
            .fillMaxWidth()

    )
}

@Composable
fun BoldedTextSkeleton(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(13.dp),
                color = SafeRecycleTheme.colors.stroke
            )
            .height(26.dp)
            .fillMaxWidth()

    )
}