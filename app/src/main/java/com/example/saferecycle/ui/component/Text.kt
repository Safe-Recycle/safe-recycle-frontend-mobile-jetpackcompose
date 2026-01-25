package com.example.saferecycle.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.saferecycle.ui.theme.SafeRecycleTheme
import com.example.saferecycle.ui.theme.fontFamily

@Composable
fun NormalText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 14.sp,
    color: Color = SafeRecycleTheme.colors.foreground
) {
    Text(
        modifier = modifier,
        text = text,
        fontFamily = fontFamily,
        fontSize = fontSize,
        color = color,
        lineHeight = 18.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun SecondaryText(
    text: String,
    fontSize: TextUnit = 12.sp,
    color: Color = SafeRecycleTheme.colors.textSecondary
) {
    Text(
        text = text,
        fontFamily = fontFamily,
        fontSize = fontSize,
        color = color,
        lineHeight = 16.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun BoldedText(
    text: String,
    fontSize: TextUnit = 20.sp,
    color: Color = SafeRecycleTheme.colors.foreground
) {
    Text(
        text = text,
        fontFamily = fontFamily,
        fontSize = fontSize,
        color = color,
        lineHeight = 26.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun MediumText(
    text: String,
    fontSize: TextUnit = 16.sp,
    color: Color = SafeRecycleTheme.colors.foreground,
    fontWeight: FontWeight = FontWeight.Medium
) {
    Text(
        text = text,
        fontFamily = fontFamily,
        fontSize = fontSize,
        color = color,
        lineHeight = 21.sp,
        textAlign = TextAlign.Center,
        fontWeight = fontWeight
    )
}