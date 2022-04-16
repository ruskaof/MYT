package com.ruskaof.myt.presentation.main.screen_new_plan

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ruskaof.myt.common.Constants
import com.ruskaof.myt.presentation.main.screen_new_plan.components.*
import com.ruskaof.myt.presentation.theme.AppTheme

@Composable
fun NewPlanScreen(
    viewModel: NewPlanScreenViewModel = hiltViewModel(),
    navController: NavController,
    context: Context
) {
    val repeatChecked = remember { mutableStateOf(false) }
    val selectedPeriod = remember { mutableStateOf(Period.DAY) }
    val timesValue = remember { mutableStateOf(2) }

    var writePlanName by remember {
        mutableStateOf("")
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (viewModel.startTimeState.value.isBefore(viewModel.endTimeState.value) && writePlanName.isNotEmpty()) {
                    if (!repeatChecked.value) {
                        viewModel.writePlan(
                            writePlanName,
                            viewModel.startTimeState.value,
                            viewModel.endTimeState.value
                        )
                        Log.d(
                            "MAIN_TAG",
                            "NewPlanScreen: adding a new plan: $writePlanName ${viewModel.startTimeState.value} ${viewModel.endTimeState.value}"
                        )
                    } else {
                        viewModel.writePlan(
                            writePlanName,
                            viewModel.startTimeState.value,
                            viewModel.endTimeState.value,
                            period = selectedPeriod.value,
                            times = timesValue.value
                        )
                        Log.d(
                            "MAIN_TAG",
                            "NewPlanScreen: adding a new plan with repeat ${timesValue.value}: $writePlanName ${viewModel.startTimeState.value} ${viewModel.endTimeState.value}"
                        )
                    }
                    navController.popBackStack()
                } else {
                    if (!viewModel.startTimeState.value.isBefore(viewModel.endTimeState.value)) {
                        Toast.makeText(
                            context,
                            "End time of plan should be after start time.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Name must not be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }, backgroundColor = AppTheme.colors.secondary) {
                Icon(Icons.Filled.Check, "Add")
            }
        },
        topBar = {
            TopBar(
                text = "New plan"
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.shapes.defaultPaddingFromStart)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            PlanNameTextField(
                caption = "Name",
                maxLength = Constants.MAX_PLAN_NAME_LENGTH,
                textState = writePlanName,
                onValueChange = {
                    if (it.length <= Constants.MAX_PLAN_NAME_LENGTH) writePlanName = it
                },
                clearText = { writePlanName = "" },
                labelsColor = AppTheme.colors.primaryTextColor,
                backgroundColor = AppTheme.colors.secondary
            )


            Spacer(modifier = Modifier.size(20.dp))

            DatePickerField(
                context = context,
                textStyle = TextStyle(
                    color = AppTheme.colors.primaryTextColor,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Light
                ),
                label = "Date",
                setDate = { year, month, day ->
                    viewModel.setStartDate(year, month, day)
                    viewModel.setEndDate(year, month, day)
                },
                time = viewModel.endTimeState.value,
                timeSurfaceColor = AppTheme.colors.secondary
            )

            Spacer(modifier = Modifier.size(20.dp))

            TimePickerField(
                context = context,
                textStyle = TextStyle(
                    color = AppTheme.colors.primaryTextColor,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Light
                ),
                label = "Start",
                setTime = { hour, minute -> viewModel.setStartTime(hour, minute) },
                //setDate = { year, month, day -> viewModel.setStartDate(year, month, day) },
                time = viewModel.startTimeState.value,
                timeSurfaceColor = AppTheme.colors.secondary
            )


            Spacer(modifier = Modifier.size(20.dp))


            TimePickerField(
                context = context,
                textStyle = TextStyle(
                    color = AppTheme.colors.primaryTextColor,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Light,
                ),
                label = "End",
                setTime = { hour, minute -> viewModel.setEndTime(hour, minute) },
                //setDate = { year, month, day -> viewModel.setEndDate(year, month, day) },
                time = viewModel.endTimeState.value,
                timeSurfaceColor = AppTheme.colors.secondary
            )

            PlanRepeatField(
                checked = repeatChecked,
                selectedPeriod = selectedPeriod,
                timesValue = timesValue
            )
        }
    }
}

