@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.ruskaof.myt.presentation.screens.screen_schedule

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.gestures.OverScrollConfiguration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ruskaof.myt.domain.model.Plan
import com.ruskaof.myt.presentation.screens.screen_main.components.NewDayHeader
import com.ruskaof.myt.presentation.screens.screen_main.components.OnLongPressDialog
import com.ruskaof.myt.presentation.screens.screen_main.components.PlanListItem
import com.ruskaof.myt.presentation.theme.AppTheme
import java.time.LocalDateTime

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScheduleScreen(
    viewModel: ScheduleScreenViewModel = hiltViewModel(),
    navController: NavController,
) {
    val listState: List<Plan> by viewModel.getAllPlans().collectAsState(initial = emptyList())
    val dialogIsOpen = remember { mutableStateOf(false) }
    val selectedPlanId = remember {
        mutableStateOf<Long>(0)
    }

    if (dialogIsOpen.value) {
        OnLongPressDialog(openDialogCustom = dialogIsOpen, onOk = {
            viewModel.removePlan(selectedPlanId.value)
        }, onCancel = {},
            backgroundColor = AppTheme.colors.primaryBackground
        )
    }

    if (listState.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "You have no plans now.\nStart by adding a new one!", style = TextStyle(
                    color = AppTheme.colors.primaryTextColor,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
    }

    CompositionLocalProvider(
        LocalOverScrollConfiguration provides OverScrollConfiguration(
            AppTheme.colors.primary
        )
    ) {
        LazyColumn(
            modifier = Modifier
                .background(AppTheme.colors.primaryBackground)
        ) {
            itemsIndexed(listState) { index, plan ->
                val nextDayStarted =
                    index == 0 || listState[index].startTime.dayOfYear != listState[index - 1].startTime.dayOfYear || listState[index].startTime.year != listState[index - 1].startTime.year
                if (nextDayStarted) {
                    val isToday = LocalDateTime.now().dayOfYear == plan.startTime.dayOfYear
                    NewDayHeader(localDateTime = plan.startTime, isToday)
                }
                PlanListItem(
                    plan = plan,
                    startTimeStyle = TextStyle(
                        color = AppTheme.colors.primaryTextColor,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Light
                    ),
                    endTimeStyle = TextStyle(
                        color = AppTheme.colors.primaryTextColor.copy(alpha = 0.7f),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Light
                    ),
                    onLongPress = {
                        selectedPlanId.value = plan.id
                        dialogIsOpen.value = true
                        Log.d("MAIN_tag", "MainScreen: ${plan.id} selected")
                    },
                    planNameTextStyle = TextStyle(
                        color = AppTheme.colors.contrastTextColor,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    ),
                    bigPaddingStart = AppTheme.shapes.bigPaddingFromStart
                )

            }
        }
    }


}
