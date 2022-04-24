package com.ruskaof.myt.presentation.main.screen_menu.screen_statistics.components.charts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BarChart(
    data: Map<String, Long>,
    modifier: Modifier
) {
    val maxValue: Long = data.values.maxByOrNull { it } ?: 0
    val minValue: Long = data.values.minByOrNull { it } ?: 0

    Box(modifier = modifier.drawBehind {

    })
}

@Preview
@Composable
fun BarChartPreview() {
    BarChart(
        data = mapOf(
            "label 1" to 10,
            "label 2" to 30,
            "label 3" to 4
        ),
        modifier = Modifier.size(40.dp)
    )
}