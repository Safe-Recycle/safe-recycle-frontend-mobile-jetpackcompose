package com.example.saferecycle.ui.screen.waste_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.ChevronLeft
import com.composables.icons.lucide.Lucide
import com.example.saferecycle.R
import com.example.saferecycle.ui.component.NormalText
import com.example.saferecycle.ui.theme.SafeRecycleTheme

@Composable
fun WasteDetailsBackClickIconButton(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(start = 28.dp, top = 9.dp)
            .size(48.dp)
            .background(
                shape = RoundedCornerShape(15.dp),
                color = SafeRecycleTheme.colors.elementBackground
            )
            .clip(RoundedCornerShape(15.dp))
            .clickable { onBackClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Lucide.ChevronLeft,
            contentDescription = "Back Icon"
        )
    }
}

@Composable
fun WastePropertyName(
    modifier: Modifier = Modifier,
    imageIdName: Int,
    contentDescription: String,
    propertyName:String
) {
    val shape = RoundedCornerShape(13.dp)
    Column(
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
                ),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                modifier = Modifier.size(42.dp),
                painter = painterResource(id = imageIdName),
                contentDescription = contentDescription
            )
        }
        NormalText(text = propertyName)
    }
}