package com.example.saferecycle.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.saferecycle.data.Category
import com.example.saferecycle.ui.theme.SafeRecycleTheme

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    category: Category,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(13.dp)
    Column(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )  { onClick() },
        verticalArrangement = spacedBy(11.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .size(75.dp)
                .clip(shape)
                .background(
                    color = SafeRecycleTheme.colors.elementBackground,
                    shape = shape
                )
//                .clickable(
//                    indication = null,
//                    interactionSource = remember { MutableInteractionSource() }
//                )  { onClick() },
            ,contentAlignment = Alignment.Center,
        ) {
            AsyncImage(
                model = category.imagePath,
                contentDescription = "${category.name} Logo",
                modifier = Modifier.size(42.dp)
            )
        }
        NormalText(text = category.name)
    }
}