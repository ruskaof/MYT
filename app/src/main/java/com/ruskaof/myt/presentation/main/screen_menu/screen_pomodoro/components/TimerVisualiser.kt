package com.ruskaof.myt.presentation.main.screen_menu.screen_pomodoro.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruskaof.myt.common.niceTime

@Composable
fun TimerVisualiser(
    currentSeconds: Int,
    secondsToEnd: Int,
    backgroundIndicatorColor: Color,
    strokeWidth: Dp,
    foregroundIndicatorColor: Color,
    textStyle: TextStyle
) {
    val minutesLeft = (currentSeconds) / 60
    val secondsLeft = (currentSeconds) % 60

    Column(
        modifier = Modifier
            .size(400.dp)
            .drawBehind {
                val canvasSize = size / 1.2f
                backgroundIndicator(
                    componentSize = canvasSize,
                    indicatorColor = backgroundIndicatorColor,
                    indicatorStrokeWidth = strokeWidth.toPx()
                )
                backgroundIndicator(
                    componentSize = canvasSize,
                    indicatorColor = foregroundIndicatorColor,
                    indicatorStrokeWidth = strokeWidth.toPx(),
                    sweepAngle = 240f * (currentSeconds.toFloat() / secondsToEnd)
                )
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = niceTime(minutesLeft, secondsLeft)
        )
    }
}

fun DrawScope.backgroundIndicator(
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float,
    sweepAngle: Float = 240f,
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}

@Preview
@Composable
fun TimerVisualiserPreview() {
    TimerVisualiser(
        currentSeconds = 60,
        secondsToEnd = 300,
        backgroundIndicatorColor = Color.Gray,
        strokeWidth = 30.dp,
        foregroundIndicatorColor = Color.Cyan,
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 200.sp
        )
    )
}