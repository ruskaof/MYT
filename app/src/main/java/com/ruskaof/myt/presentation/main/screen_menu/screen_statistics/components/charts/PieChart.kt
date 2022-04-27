package com.ruskaof.myt.presentation.main.screen_menu.screen_statistics.components.charts

import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PieChart(
    modifier: Modifier = Modifier.size(300.dp),
    items: List<PieChartItem> = listOf(
        PieChartItem("mango", 10, Color.Black),
        PieChartItem("banana", 5, Color.Yellow),
        PieChartItem("pie", 7, Color.Cyan)
    ),
    isAlreadyShown: Boolean = false
) {
    var pieChartIsShown by remember { mutableStateOf(isAlreadyShown) }


    val animatedPieWidthPre by animateFloatAsState(
        targetValue = if (pieChartIsShown) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1500)
    )
    LaunchedEffect(key1 = true) {
        pieChartIsShown = true
    }

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
        it.value * 100 / sumOfValues
    }

    // Convert each proportions to angle
    val sweepAngles = proportions.map {
        360 * animatedPieWidthPre * it / 100
    }



    Column {
        Canvas(
            modifier = modifier
        ) {

            var startAngle = -90f * animatedPieWidthPre

            for (i in sweepAngles.indices) {
                drawArc(
                    color = items[i].color,
                    startAngle = startAngle,
                    sweepAngle = sweepAngles[i],
                    useCenter = true,

                )
                startAngle += sweepAngles[i]
            }

        }
        for (i in items.indices) {
            DisplayLegend(color = items[i].color, legend = items[i].name)
        }
    }

}

@Composable
fun DisplayLegend(color: Color, legend: String) {

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

@Preview
@Composable
fun PieChartPreview() {
    PieChart(isAlreadyShown = true)
}