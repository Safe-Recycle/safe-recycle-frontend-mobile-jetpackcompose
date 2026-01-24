package com.example.saferecycle.ui.screen.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.saferecycle.R
import com.example.saferecycle.ui.theme.LocalSafeRecycleColors
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToLoginOrHome: () -> Unit
) {
    val colors = LocalSafeRecycleColors.current

    var startExitAnimation by remember { mutableStateOf(false) }

    val alpha by animateFloatAsState(
        targetValue = if (startExitAnimation) 0f else 1f,
        animationSpec = tween(200), label = "alpha"
    )
    val scale by animateFloatAsState(
        targetValue = if (startExitAnimation) 0.8f else 1f,
        animationSpec = tween(200), label = "scale"
    )

    LaunchedEffect(Unit) {
        delay(1200)
        delay(10)
        onNavigateToLoginOrHome()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.accent),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_white),
            contentDescription = "Safe&Recycle Logo",
            modifier = Modifier
                .scale(scale)
                .alpha(alpha)
                .height(79.dp)
                .width(255.dp)
        )
    }
}