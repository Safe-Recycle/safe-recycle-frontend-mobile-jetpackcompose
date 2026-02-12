package com.example.saferecycle.ui.component

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.saferecycle.ui.theme.SafeRecycleTheme

@Composable
fun NormalButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    color: Color = SafeRecycleTheme.colors.accent,
    contentColor: Color = SafeRecycleTheme.colors.elementBackground,
    icon: ImageVector? = null,
    contentDescription: String? = null,
    isLoading: Boolean? = null
) {
    val shape = RoundedCornerShape(13.dp)

    Box(
        modifier = Modifier
            .background(
                shape = shape,
                color = color
            )
            .clip(shape)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 14.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isLoading == true) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = SafeRecycleTheme.colors.elementBackground
                )
            } else {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = contentDescription,
                        tint = contentColor
                    )
                }
                Spacer(Modifier.width(11.dp))
                MediumText(text = text, color = contentColor)
            }
        }
    }
}