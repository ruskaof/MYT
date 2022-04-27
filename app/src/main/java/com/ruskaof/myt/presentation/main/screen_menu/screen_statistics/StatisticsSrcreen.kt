package com.ruskaof.myt.presentation.main.screen_menu.screen_statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ruskaof.myt.domain.model.Plan
import com.ruskaof.myt.presentation.main.screen_menu.screen_statistics.components.charts.BarChart
import com.ruskaof.myt.presentation.main.screen_menu.screen_statistics.components.charts.PieChart1
import com.ruskaof.myt.presentation.main.screen_menu.screen_statistics.components.charts.PieChartItem
import com.ruskaof.myt.presentation.main.screen_menu.screen_statistics.components.charts.colorCollection
import com.ruskaof.myt.presentation.main.screen_new_plan.components.TopBar
import com.ruskaof.myt.presentation.theme.AppTheme

@Composable
fun StatisticsScreen(
    viewModel: StatisticScreenViewModel = hiltViewModel()
) {
    val beforeTodayListState: List<Plan> by viewModel.getAllPlansBeforeToday()
        .collectAsState(initial = emptyList())
    val barStats =
        if (beforeTodayListState.isEmpty()) emptyList() else viewModel.calculateTimeMinutes(
            beforeTodayListState
        )
            .subList(0, kotlin.math.min(5, beforeTodayListState.size))

    val allListState: List<Plan> by viewModel.getAllPlansBeforeToday()
        .collectAsState(initial = emptyList())
    val pieStats = viewModel.calculateTimeMinutes(allListState)
    val pieChartItems by derivedStateOf {
        run {
            val list = mutableListOf<PieChartItem>()
            for (item in pieStats) {
                list.add(PieChartItem(item.first, item.second, colorCollection.random()))
            }
            list.toList()
        }
    }


    Scaffold(
        topBar = { TopBar(text = "Stats") }
    ) {
        if (beforeTodayListState.isEmpty() || beforeTodayListState.size == 1) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Looks like we can't find any plans for stats.\nKeep managing your time till you have something in your archive!",
                    style = TextStyle(
                        color = AppTheme.colors.primaryTextColor,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
        } else {
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
                    data = barStats,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(Color.Red),
                    barsColor = AppTheme.colors.secondary,
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Overall stats by name:", style = TextStyle(
                        color = AppTheme.colors.primaryTextColor,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center
                    )
                )
                PieChart1(
                    items = pieChartItems
                )
            }
        }
    }
}