package com.ruskaof.myt.presentation.main.screen_new_plan

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ruskaof.myt.presentation.main.screen_new_plan.components.DateTimePickerField
import com.ruskaof.myt.presentation.main.screen_new_plan.components.PlanNameTextField
import com.ruskaof.myt.presentation.main.screen_new_plan.components.TopBar
import com.ruskaof.myt.presentation.theme.AppTheme

@Composable
fun NewPlanScreen(
    viewModel: NewPlanScreenViewModel = hiltViewModel(),
    navController: NavController,
    context: Context
) {
    var writePlanName by remember {
        mutableStateOf("")
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.writePlan(
                    writePlanName,
                    viewModel.startTimeState.value,
                    viewModel.endTimeState.value
                )
                navController.popBackStack()
            }, backgroundColor = AppTheme.colors.secondary) {
                Icon(Icons.Filled.Check, "Add")
            }
        },
        topBar = {
            TopBar(
                text = "New plan"
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.shapes.defaultPaddingFromStart),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            PlanNameTextField(
                caption = "Name",
                maxLength = 20,
                textState = writePlanName,
                onValueChange = { if (it.length <= 20) writePlanName = it },
                clearText = { writePlanName = "" },
                labelsColor = AppTheme.colors.primaryTextColor,
                backgroundColor = AppTheme.colors.secondary
            )
            Spacer(modifier = Modifier.size(20.dp))
            DateTimePickerField(
                context = context,
                textStyle = TextStyle(
                    color = AppTheme.colors.primaryTextColor,
                    fontSize = 30.sp
                ),
                label = "Start",
                setTime = { hour, minute -> viewModel.setStartTime(hour, minute) },
                setDate = { year, month, day -> viewModel.setStartDate(year, month, day) },
                time = viewModel.startTimeState.value,
                timeSurfaceColor = AppTheme.colors.secondary
            )
            Spacer(modifier = Modifier.size(20.dp))
            DateTimePickerField(
                context = context,
                textStyle = TextStyle(
                    color = AppTheme.colors.primaryTextColor,
                    fontSize = 30.sp
                ),
                label = "End",
                setTime = { hour, minute -> viewModel.setEndTime(hour, minute) },
                setDate = { year, month, day -> viewModel.setEndDate(year, month, day) },
                time = viewModel.endTimeState.value,
                timeSurfaceColor = AppTheme.colors.secondary
            )
        }
    }


}