package com.ruskaof.myt.presentation.main.screen_menu.screen_pomodoro

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            secondsToEnd = if (viewModel.isNowWorkTime) viewModel.workPeriodMills / 1000 else viewModel.restPeriodMills / 1000,
            strokeWidth = 20.dp,
            foregroundIndicatorColor = AppTheme.colors.primary,
            textStyle = TextStyle(
                color = AppTheme.colors.primaryTextColor,
                fontSize = 30.sp,
            ),
            backgroundIndicatorColor = Color.Gray
        )

        Text(text = "periods passed: ${viewModel.periodsPassed.value / 2}")

        GridOfButtons(viewModel = viewModel)

    }
}


@Composable
private fun GridOfButtons(viewModel: PomodoroScreenViewModel) {
    val buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        disabledBackgroundColor = Color.Gray,
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {

            OutlinedButton(
                onClick = {
                    viewModel.startTimer()
                },
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth(),
                enabled = viewModel.startButtonEnabled,
                colors = buttonColors
            ) {
                Text(
                    "Start",
                    style = TextStyle(
                        color = AppTheme.colors.primaryTextColor,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Light
                    )
                )
            }

            OutlinedButton(
                onClick = { viewModel.skipCurrent() },
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth(),
                enabled = viewModel.skipButtonEnabled,
                colors = buttonColors
            ) {
                Text(
                    "Skip",
                    style = TextStyle(
                        color = AppTheme.colors.primaryTextColor,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Light
                    )
                )
            }
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            OutlinedButton(
                onClick = { viewModel.pause() },
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth(),
                enabled = viewModel.pauseButtonEnabled,
                colors = buttonColors
            ) {
                Text(
                    "Pause",
                    style = TextStyle(
                        color = AppTheme.colors.primaryTextColor,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Light
                    )
                )
            }

            OutlinedButton(
                onClick = { viewModel.resume() },
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth(),
                enabled = viewModel.resumeButtonEnabled,
                colors = buttonColors
            ) {
                Text(
                    "Resume",
                    style = TextStyle(
                        color = AppTheme.colors.primaryTextColor,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Light
                    )
                )
            }
        }
    }
}
