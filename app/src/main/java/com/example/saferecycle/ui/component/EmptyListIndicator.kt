package com.example.saferecycle.ui.component

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.BadgeInfo
import com.composables.icons.lucide.Lucide
import com.example.saferecycle.ui.component.SecondaryText
import com.example.saferecycle.ui.theme.SafeRecycleTheme

@Composable
fun EmptyListIndicator(text: String) {
    Column(
        verticalArrangement = spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Lucide.BadgeInfo,
            contentDescription = "Empty List Icon",
            tint = SafeRecycleTheme.colors.textSecondary
        )
        SecondaryText(text)
    }
}