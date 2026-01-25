package com.example.saferecycle.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.saferecycle.data.Waste

@Composable
fun WasteCard(waste: Waste, onClick: () -> Unit) {
    val shape = RoundedCornerShape(13.dp)

    Column(
        modifier = Modifier
        .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) { onClick() },
        verticalArrangement = spacedBy(11.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(163.dp)
                .clip(shape),
//                .clickable(
//                    indication = null,
//                    interactionSource = remember { MutableInteractionSource() }
//                ) { onClick() },
            model = waste.imagePath,
            contentDescription = "${waste.name} Image",
            contentScale = ContentScale.Crop
        )
        Column {
            NormalText(text = waste.name)
            SecondaryText(text = waste.category.name)
        }
    }
}