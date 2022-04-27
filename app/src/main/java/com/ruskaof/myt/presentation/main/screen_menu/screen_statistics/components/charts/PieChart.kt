package com.ruskaof.myt.presentation.main.screen_menu.screen_statistics.components.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PieChart1(
    items: List<PieChartItem> = listOf(
        PieChartItem("mango", 10, Color.Black),
        PieChartItem("banana", 5, Color.Yellow),
        PieChartItem("pie", 7, Color.Cyan)
    ),
    size: Dp = 200.dp
) {

    // Sum of all the values
    val sumOfValues by remember {
        mutableStateOf(
            run {
                var sum = 0f
                for (item in items) {
                    sum += item.value
                }
                sum
            }
        )
    }

    // Calculate each proportion value
    val proportions = items.map {
        it.value * 100 / sumOfValues.toFloat()
    }

    // Convert each proportions to angle
    val sweepAngles = proportions.map {
        360 * it / 100
    }

    Canvas(
        modifier = Modifier
            .size(size = size)
    ) {

        var startAngle = -90f

        for (i in sweepAngles.indices) {
            drawArc(
                color = items[i].color,
                startAngle = startAngle,
                sweepAngle = sweepAngles[i],
                useCenter = true
            )
            startAngle += sweepAngles[i]
        }

    }

    Spacer(modifier = Modifier.height(32.dp))

    Column {
        for (i in items.indices) {
            DisplayLegend1(color = items[i].color, legend = items[i].name)
        }
    }

}

@Composable
fun DisplayLegend1(color: Color, legend: String) {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier.width(16.dp),
            thickness = 4.dp,
            color = color
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = legend,
            color = Color.Black
        )
    }
}