package com.ruskaof.myt.presentation.main.screen_menu.screen_statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ruskaof.myt.domain.model.Plan
import com.ruskaof.myt.presentation.main.screen_menu.screen_statistics.components.charts.BarChart
import com.ruskaof.myt.presentation.main.screen_new_plan.components.TopBar
import com.ruskaof.myt.presentation.theme.AppTheme

@Composable
fun StatisticsScreen(
    viewModel: StatisticScreenViewModel = hiltViewModel()
) {
    val listState: List<Plan> by viewModel.getAllPlans().collectAsState(initial = emptyList())
    val stats = if (listState.isEmpty()) emptyList() else viewModel.calculateTimeMinutes(listState)
        .subList(0, kotlin.math.min(5, listState.size - 1))

    Scaffold(
        topBar = { TopBar(text = "Stats") }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Top plans by time:", style = TextStyle(
                    color = AppTheme.colors.primaryTextColor,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
            )
            BarChart(
                data = stats,
                modifier = Modifier
                    .size(300.dp)
                    .fillMaxWidth(),
                barsColor = AppTheme.colors.secondary
            )
        }
    }
}