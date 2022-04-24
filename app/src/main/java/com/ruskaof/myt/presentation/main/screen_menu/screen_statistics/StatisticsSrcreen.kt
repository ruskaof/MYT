package com.ruskaof.myt.presentation.main.screen_menu.screen_statistics

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ruskaof.myt.domain.model.Plan
import com.ruskaof.myt.presentation.main.screen_menu.screen_statistics.components.charts.BarChart
import com.ruskaof.myt.presentation.main.screen_new_plan.components.TopBar

@Composable
fun StatisticsScreen(
    viewModel: StatisticScreenViewModel = hiltViewModel()
) {
    val listState: List<Plan> by viewModel.getAllPlans().collectAsState(initial = emptyList())
    val stats = viewModel.calculateTimeMinutes(listState)

    Scaffold(
        topBar = { TopBar(text = "Stats") }
    ) {
        BarChart(data = stats, modifier = Modifier.fillMaxSize())
    }
}