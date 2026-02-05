package com.example.saferecycle.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.saferecycle.ui.theme.SafeRecycleTheme

@Composable
fun WasteCardSkeleton(modifier: Modifier = Modifier) {
    val shape = RoundedCornerShape(13.dp)

    Column(
        modifier = Modifier.width(163.dp),
        verticalArrangement = spacedBy(11.dp)
    ) {
        Box(
            modifier = Modifier.background(
                shape = shape,
                color = SafeRecycleTheme.colors.stroke
            ).size(163.dp)
        )
        Column(verticalArrangement = spacedBy(2.dp)) {
            NormalTextSkeleton(modifier = Modifier.fillMaxWidth(0.4f))
            SecondaryTextSkeleton(modifier = Modifier.fillMaxWidth(0.2f))
        }
    }
}