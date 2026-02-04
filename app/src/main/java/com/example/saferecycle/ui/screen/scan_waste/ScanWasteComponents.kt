package com.example.saferecycle.ui.screen.scan_waste

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Flashlight
import com.composables.icons.lucide.FlashlightOff
import com.composables.icons.lucide.Image
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Search
import com.example.saferecycle.ui.theme.SafeRecycleTheme

@Composable
fun ShutterButton(modifier: Modifier = Modifier, onCLick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(
                shape = CircleShape,
                color = SafeRecycleTheme.colors.elementBackground.copy(alpha = 0.35f)
            )
            .padding(6.dp)
            .clip(CircleShape)
            .clickable { onCLick() }
    ) {
        Box(
            modifier = Modifier
                .background(
                    shape = CircleShape,
                    color = SafeRecycleTheme.colors.elementBackground
                )
                .padding(23.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(33.dp),
                imageVector = Lucide.Search,
                contentDescription = "Icon for Scan Waste Shutter",
                tint = SafeRecycleTheme.colors.foreground
            )
        }
    }
}

@Preview
@Composable
private fun ShutterButtonPreview() {
    ShutterButton(onCLick = {})
}

@Composable
fun CameraOptionButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(15.dp)
    Box(
        modifier = Modifier
            .clip(shape)
            .clickable { onClick() }
            .background(
                shape = shape,
                color = SafeRecycleTheme.colors.foreground.copy(alpha = 0.32f)
            )
            .padding(25.dp)

    ) {
        Icon(
            modifier = Modifier.size(27.dp),
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = SafeRecycleTheme.colors.elementBackground
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CameraOptionButtonPreview() {
    CameraOptionButton(
        imageVector = Lucide.Image,
        contentDescription = "Icon for Get Photo from Gallery",
        onClick = {}
    )
}

@Composable
fun ScanWasteBottomBar(
    modifier: Modifier = Modifier,
    onFlashButtonClick: () -> Unit,
    onShutterButtonClick: () -> Unit,
    onSelectImageButtonClick: () -> Unit,
    isFlashOn: Boolean
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        CameraOptionButton(
            imageVector = if (isFlashOn) Lucide.FlashlightOff else Lucide.Flashlight,
            contentDescription = "Icon for Flashlight",
            onClick = { onFlashButtonClick() }
        )
        ShutterButton(onCLick = { onShutterButtonClick() })
        CameraOptionButton(
            imageVector = Lucide.Image,
            contentDescription = "Icon for Get Photo from Gallery",
            onClick = { onSelectImageButtonClick() }
        )
    }
}

private fun DrawScope.drawCorner(
    start: Offset,
    horizontal: Boolean,
    length: Float,
    strokeWidth: Float,

    ) {
    val end = if (horizontal) {
        start + Offset(length, 0f)
    } else {
        start + Offset(0f, length)
    }

    drawLine(
        color = Color.White,
        start = start,
        end = end,
        strokeWidth = strokeWidth
    )
}

@Composable
fun CameraOverlay(
    cutoutWidth: Dp = 301.dp,
    cutoutHeight: Dp = 489.dp,
    cornerRadius: Dp = 0.dp
) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer { alpha = 0.99f }
    ) {
        // Overlay gelap
        drawRect(
            color = Color.Black.copy(alpha = 0.5f)
        )

        val widthPx = cutoutWidth.toPx()
        val heightPx = cutoutHeight.toPx()

        val left = (size.width - widthPx) / 2f
        val top = 133.dp.toPx()

        val stroke = 5.dp.toPx()
        val cornerLength = 45.dp.toPx()

        val right = left + widthPx
        val bottom = top + heightPx

        // Lubang transparan
        drawRoundRect(
            color = Color.Transparent,
            topLeft = Offset(left, top),
            size = Size(widthPx, heightPx),
            cornerRadius = CornerRadius(
                cornerRadius.toPx(),
                cornerRadius.toPx()
            ),
            blendMode = BlendMode.Clear
        )

        // ─── Corner putih ───

        // Top-left
        drawCorner(Offset(left - 6f, top), true, cornerLength, stroke)
        drawCorner(Offset(left, top - 6f), false, cornerLength, stroke)

        // Top-right
        drawCorner(
            Offset(right - cornerLength + 6f, top),
            true,
            cornerLength,
            stroke
        )
        drawCorner(Offset(right, top - 6f), false, cornerLength, stroke)

        // Bottom-left
        drawCorner(Offset(left - 6f, bottom), true, cornerLength, stroke)
        drawCorner(
            Offset(left, bottom - cornerLength + 6f),
            false,
            cornerLength,
            stroke,
        )

        // Bottom-right
        drawCorner(
            Offset(right - cornerLength + 6f, bottom),
            true,
            cornerLength,
            stroke,

            )
        drawCorner(
            Offset(right, bottom - cornerLength + 6f),
            false,
            cornerLength,
            stroke,

            )
    }
}
//@Composable
//fun CameraOverlay(
//    cutoutWidth: Dp = 301.dp,
//    cutoutHeight: Dp = 489.dp,
//    cornerRadius: Dp = 16.dp
//) {
//
//    Canvas(modifier = Modifier.fillMaxSize()) {
//
//        // Overlay gelap
//        drawRect(
//            color = Color.Black.copy(alpha = 0.5f)
//        )
//
//        val widthPx = cutoutWidth.toPx()
//        val heightPx = cutoutHeight.toPx()
//
//        val left = (size.width - widthPx) / 2f
//        val top = 133.dp.toPx()
//
//        // Lubang transparan
//        drawRoundRect(
//            color = Color.Transparent,
//            topLeft = Offset(left, top),
//            size = Size(widthPx, heightPx),
//            cornerRadius = CornerRadius(
//                cornerRadius.toPx(),
//                cornerRadius.toPx()
//            ),
//            blendMode = BlendMode.Clear
//        )
//    }
//}