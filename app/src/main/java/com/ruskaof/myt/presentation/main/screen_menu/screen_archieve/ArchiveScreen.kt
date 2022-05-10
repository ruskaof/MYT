package com.ruskaof.myt.presentation.main.screen_menu.screen_archieve

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ruskaof.myt.domain.model.Plan
import com.ruskaof.myt.presentation.main.screen_main.components.NewDayHeader
import com.ruskaof.myt.presentation.main.screen_main.components.PlanListItem
import com.ruskaof.myt.presentation.main.screen_menu.screen_archieve.components.RedactionPlanDialog
import com.ruskaof.myt.presentation.main.screen_new_plan.components.TopBar
import com.ruskaof.myt.presentation.theme.AppTheme
import java.time.LocalDateTime

@Composable
fun ArchiveScreen(
    viewModel: ArchiveScreenViewModel = hiltViewModel(),
    navController: NavController,
    context: Context
) {
    BackHandler {
        navController.popBackStack(
            saveState = true,
            destinationId = navController.currentDestination!!.id,
            inclusive = true
        )
    }

    val listState: List<Plan> by viewModel.getAllPlans().collectAsState(initial = emptyList())
    val dialogIsOpen = remember { mutableStateOf(false) }
    val selectedPlan = remember {
        mutableStateOf(Plan("error", LocalDateTime.MIN, LocalDateTime.MAX))
    }
    val newNameOfPlanState = remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = { TopBar(text = "Archive") },
    ) {
        if (dialogIsOpen.value) {
            RedactionPlanDialog(openDialogCustom = dialogIsOpen, onOk = {
                if (newNameOfPlanState.value.isNotEmpty()) {
                    viewModel.updatePlan(selectedPlan.value.copy(name = newNameOfPlanState.value.trim()))
                    newNameOfPlanState.value = ""
                } else {
                    Toast.makeText(
                        context,
                        "Name of plan must not be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }, onCancel = {}, "Write a new plan name", newNameOfPlanState)
        }


        LazyColumn(modifier = Modifier.background(AppTheme.colors.primaryBackground)) {
            itemsIndexed(listState) { index, plan ->
                val nextDayStarted =
                    index == 0
                            || listState[index].startTime.dayOfYear != listState[index - 1].startTime.dayOfYear
                            || listState[index].startTime.year != listState[index - 1].startTime.year
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
                        selectedPlan.value = plan
                        dialogIsOpen.value = true
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