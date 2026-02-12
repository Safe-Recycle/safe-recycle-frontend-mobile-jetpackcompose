package com.example.saferecycle.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.CircleAlert
import com.composables.icons.lucide.Lucide
import com.example.saferecycle.ui.theme.SafeRecycleTheme

@Composable
fun ErrorField(
    modifier: Modifier = Modifier, errorMessage: String,
    isVisible: Boolean = false
) {
    val shape = RoundedCornerShape(13.dp)
    Box(
        Modifier
            .background(
                shape = shape,
                color = if (isVisible) SafeRecycleTheme.colors.dangerBackground else Color.Transparent,
            )
            .border(
                shape = shape,
                color = if (isVisible) SafeRecycleTheme.colors.danger else Color.Transparent,
                width = 1.dp
            )
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = spacedBy(10.dp)
        ) {
            Icon(
                modifier = Modifier.size(17.dp),
                imageVector = Lucide.CircleAlert,
                contentDescription = "Icon for login error",
                tint = if (isVisible) SafeRecycleTheme.colors.danger else Color.Transparent
            )
            NormalText(
                text = errorMessage,
                color = if (isVisible) SafeRecycleTheme.colors.danger else Color.Transparent
            )
        }
    }
}