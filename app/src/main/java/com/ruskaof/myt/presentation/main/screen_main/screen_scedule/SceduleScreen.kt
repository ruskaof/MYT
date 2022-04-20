package com.ruskaof.myt.presentation.main.screen_main.screen_scedule

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ruskaof.myt.presentation.main.screen_main.components.NewDayHeader
import com.ruskaof.myt.presentation.main.screen_main.components.OnLongPressDialog
import com.ruskaof.myt.presentation.main.screen_main.components.PlanListItem
import com.ruskaof.myt.presentation.theme.AppTheme
import java.time.LocalDateTime

@Composable
fun ScheduleScreen(
    viewModel: ShceduleScreenViewModel = hiltViewModel()
) {
    val listState = viewModel.getAllPlans().collectAsState(initial = emptyList())
    val dialogIsOpen = remember { mutableStateOf(false) }
    val selectedPlanId = remember {
        mutableStateOf<Long?>(null)
    }

    if (dialogIsOpen.value) {
        OnLongPressDialog(openDialogCustom = dialogIsOpen, onOk = {
            viewModel.removePlan(selectedPlanId.value!!)
        }, onCancel = {})
    }


    LazyColumn(
        modifier = Modifier.background(AppTheme.colors.primaryBackground),
    ) {
        itemsIndexed(listState.value) { index, plan ->
            val nextDayStarted =
                index == 0 || listState.value[index].startTime.dayOfMonth != listState.value[index - 1].startTime.dayOfMonth || listState.value[index].startTime.month != listState.value[index - 1].startTime.month || listState.value[index].startTime.year != listState.value[index - 1].startTime.year
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
                onLongPress = { id ->
                    selectedPlanId.value = id
                    dialogIsOpen.value = true
                },
                planNameTextStyle = TextStyle(
                    color = AppTheme.colors.contrastTextColor,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                ),
                paddingStart = AppTheme.shapes.bigPaddingFromStart
            )

        }
    }
}