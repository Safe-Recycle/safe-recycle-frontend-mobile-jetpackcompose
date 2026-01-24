package com.example.saferecycle.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.runtime.SideEffect

data class SafeRecycleColors(
    val accentDark: Color,
    val accent: Color,
    val accentLight: Color,
    val bright: Color,
    val elementBackground: Color,
    val foreground: Color,
    val stroke: Color,
    val background: Color,
    val textSecondary:Color
)

val LightSafeRecycleColors = SafeRecycleColors(
    accentDark = AccentDark,
    accent = Accent,
    accentLight = AccentLight,
    bright = Bright,
    elementBackground = ElementBackground,
    foreground = Foreground,
    stroke = Stroke,
    background = Background,
    textSecondary = TextSecondary
)

val LocalSafeRecycleColors = staticCompositionLocalOf {
    LightSafeRecycleColors
}

@Composable
fun SetStatusBarIconsDark() {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent, // atau warna background kamu
            darkIcons = true // <- INI KUNCINYA
        )
    }
}

object SafeRecycleTheme {
    val colors: SafeRecycleColors
        @Composable
        get() = LocalSafeRecycleColors.current
}


@Composable
fun SafeRecycleTheme(
    content: @Composable () -> Unit
) {
    SetStatusBarIconsDark()

    CompositionLocalProvider(
        LocalSafeRecycleColors provides LightSafeRecycleColors
    ) {
        MaterialTheme(
            colorScheme = SafeRecycleColorScheme,
            typography = Typography,
            content = content
        )
    }
}

private val SafeRecycleColorScheme = lightColorScheme(
    primary = Accent,
    onPrimary = ElementBackground,

    secondary = AccentLight,
    onSecondary = Foreground,

    background = Background,
    onBackground = Foreground,

    surface = ElementBackground,
    onSurface = Foreground,

    outline = Stroke
)

//private val DarkColorScheme = darkColorScheme(
//    primary = Purple80,
//    secondary = PurpleGrey80,
//    tertiary = Pink80
//)
//
//private val LightColorScheme = lightColorScheme(
//    primary = Purple40,
//    secondary = PurpleGrey40,
//    tertiary = Pink40
//
//    /* Other default colors to override
//    background = Color(0xFFFFFBFE),
//    surface = Color(0xFFFFFBFE),
//    onPrimary = Color.White,
//    onSecondary = Color.White,
//    onTertiary = Color.White,
//    onBackground = Color(0xFF1C1B1F),
//    onSurface = Color(0xFF1C1B1F),
//    */
//)


//@Composable
//fun SafeRecycleTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
//    content: @Composable () -> Unit
//) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(
//                context
//            )
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography,
//        content = content
//    )
//}