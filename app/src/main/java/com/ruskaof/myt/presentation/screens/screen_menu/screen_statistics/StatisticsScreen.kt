package com.ruskaof.myt.presentation.screens.screen_menu.screen_statistics

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ruskaof.myt.domain.model.Plan
import com.ruskaof.myt.presentation.screens.screen_menu.screen_statistics.components.charts.BarChart
import com.ruskaof.myt.presentation.screens.screen_menu.screen_statistics.components.charts.PieChart
import com.ruskaof.myt.presentation.screens.screen_menu.screen_statistics.components.charts.PieChartItem
import com.ruskaof.myt.presentation.screens.screen_menu.screen_statistics.components.charts.colorCollection
import com.ruskaof.myt.presentation.screens.screen_new_plan.components.TopBar
import com.ruskaof.myt.presentation.theme.AppTheme

@Composable
fun StatisticsScreen(
    viewModel: StatisticScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    BackHandler {
        navController.popBackStack(
            saveState = true,
            destinationId = navController.currentDestination!!.id,
            inclusive = true
        )
    }

    val beforeTodayListState: List<Plan> by viewModel.getAllPlansBeforeToday()
        .collectAsState(initial = emptyList())
    val barStats =
        if (beforeTodayListState.isEmpty()) emptyList() else viewModel.calculateTimeMinutesSorted(
            beforeTodayListState
        )

    val pieStats = viewModel.calculateMinutesOfFirstSixAndOthersSorted(beforeTodayListState)
    val pieChartItems by derivedStateOf {
        run {
            val list = mutableListOf<PieChartItem>()
            for (i in pieStats.indices) {
                list.add(
                    PieChartItem(
                        pieStats[i].first,
                        pieStats[i].second,
                        colorCollection[i % 7]
                    )
                )
            }
            list.toList()
        }
    }


    Scaffold(
        topBar = { TopBar(text = "Stats") },
        backgroundColor = AppTheme.colors.primaryBackground
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
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppTheme.shapes.defaultPaddingFromStart),
                    border = BorderStroke(2.dp, AppTheme.colors.primary)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Overall stats", style = TextStyle(
                                color = AppTheme.colors.primaryTextColor,
                                fontSize = 30.sp,
                                textAlign = TextAlign.Center
                            )
                        )
                        PieChart(
                            items = pieChartItems,
                            modifier = Modifier
                                .aspectRatio(1f)
                                .padding(5.dp),
                            textColor = AppTheme.colors.primaryTextColor
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppTheme.shapes.defaultPaddingFromStart),
                    border = BorderStroke(2.dp, AppTheme.colors.primary)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Top plans by time", style = TextStyle(
                                color = AppTheme.colors.primaryTextColor,
                                fontSize = 30.sp,
                                textAlign = TextAlign.Center
                            )
                        )
                        BarChart(
                            data = barStats,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height((barStats.size * 50).dp),
                            barsColor = AppTheme.colors.secondary,
                            perceptibleColoredTextColor = AppTheme.colors.primaryTextColor
                        )
                    }
                }
            }
        }
    }
}