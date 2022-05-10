package com.ruskaof.myt.presentation.main.screen_menu.screen_pomodoro

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ruskaof.myt.presentation.main.screen_menu.screen_pomodoro.components.TimerVisualiser
import com.ruskaof.myt.presentation.theme.AppTheme

@Composable
fun PomodoroScreen(
    viewModel: PomodoroScreenViewModel = hiltViewModel()
) {
    Column {

        TimerVisualiser(
            currentSeconds = viewModel.currentSeconds.value,
            secondsToEnd = viewModel.periodLengthMills / 60,
            backgroundIndicatorColor = Color.Gray,
            strokeWidth = 20.dp,
            foregroundIndicatorColor = Color.Cyan,
            textStyle = TextStyle(
                color = AppTheme.colors.primaryTextColor,
                fontSize = 30.sp,
            )
        )

        Button(onClick = { viewModel.start() }) {

        }
    }
}