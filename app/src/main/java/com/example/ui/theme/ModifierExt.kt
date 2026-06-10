package com.example.ui.theme

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.glow(
    color: Color = GlowColor,
    radius: Dp = 8.dp,
    alpha: Float = 0.5f
) = this.drawBehind {
    val paint = Paint().apply {
        val frameworkPaint = this.asFrameworkPaint()
        frameworkPaint.color = color.copy(alpha = alpha).toArgb()
        frameworkPaint.maskFilter = BlurMaskFilter(radius.toPx(), BlurMaskFilter.Blur.NORMAL)
    }
    drawIntoCanvas { canvas ->
        canvas.drawRoundRect(
            left = 0f,
            top = 0f,
            right = size.width,
            bottom = size.height,
            radiusX = 16.dp.toPx(),
            radiusY = 16.dp.toPx(),
            paint = paint
        )
    }
}
