package com.ruskaof.myt.presentation.main.screen_menu.screen_statistics.components.charts

import android.graphics.Typeface
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToLong

@Composable
fun BarChart(
    data: List<Pair<String, Long>>,
    modifier: Modifier,
    axisWidth: Dp = 2.dp
) {
    val maxValue: Long = data.maxByOrNull { it.second }?.second?.times(1.2f)?.roundToLong() ?: 0
    val minValue: Long = data.minByOrNull { it.second }?.second?.times(0.8f)?.roundToLong() ?: 0

    Log.d("MAIN_TAG", "BarChart: $data")

    Canvas(modifier = modifier) {
        val textPaint = Paint().asFrameworkPaint().apply {
            isAntiAlias = true
            textSize = 20.sp.toPx()
            color = android.graphics.Color.BLUE
            typeface = Typeface.create(Typeface.MONOSPACE, Typeface.BOLD)
        }

        drawLine(
            start = Offset(
                x = 0f,
                y = size.height
            ),
            end = Offset(
                x = size.width,
                y = size.height
            ),
            color = Color.Black,
            strokeWidth = axisWidth.toPx()
        )

        drawLine(
            start = Offset(
                x = 0f,
                y = size.height
            ),
            end = Offset(
                x = 0f,
                y = 0f
            ),
            color = Color.Black,
            strokeWidth = axisWidth.toPx()
        )

        val lineWidth = size.width / data.size

        for (i in data.indices) {

            drawRect(
                color = Color.Red,
                topLeft = Offset(
                    y = size.height - size.height * (data[i].second - minValue) / (maxValue - minValue),
                    x = i * lineWidth
                ),
                size = Size(
                    height = size.height * (data[i].second - minValue) / (maxValue - minValue),
                    width = lineWidth
                )
            )

            drawIntoCanvas {
                it.nativeCanvas.drawText(
                    data[i].first,
                    i * lineWidth,
                    size.height,
                    textPaint
                )
            }
        }
    }
}

@Preview
@Composable
fun BarChartPreview() {
    BarChart(
        data = listOf(
            "label 1" to 10,
            "label 2" to 30,
            "label 3" to 4
        ),
        modifier = Modifier.size(300.dp)
    )
}
