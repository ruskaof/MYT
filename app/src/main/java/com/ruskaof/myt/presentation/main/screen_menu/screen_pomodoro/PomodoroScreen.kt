package com.ruskaof.myt.presentation.main.screen_menu.screen_pomodoro

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ruskaof.myt.presentation.main.screen_menu.screen_pomodoro.components.TimerVisualiser
import com.ruskaof.myt.presentation.theme.AppTheme

@Composable
fun PomodoroScreen(
    viewModel: PomodoroScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    BackHandler {
        navController.popBackStack(
            saveState = true,
            destinationId = navController.currentDestination!!.id,
            inclusive = true
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TimerVisualiser(
            currentSeconds = viewModel.currentSeconds.value,
            secondsToEnd = viewModel.workPeriodMills / 1000,
            strokeWidth = 20.dp,
            foregroundIndicatorColor = AppTheme.colors.primary,
            textStyle = TextStyle(
                color = AppTheme.colors.primaryTextColor,
                fontSize = 30.sp,
            ),
            backgroundIndicatorColor = Color.Gray
        )

        OutlinedButton(onClick = {
            viewModel.startTimer()
        }) {
            Text(
                "Start",
                style = TextStyle(
                    color = AppTheme.colors.primaryTextColor,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Light
                )
            )
        }
    }
}