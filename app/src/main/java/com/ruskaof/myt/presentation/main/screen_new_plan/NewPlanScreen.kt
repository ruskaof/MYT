package com.ruskaof.myt.presentation.main.screen_new_plan

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ruskaof.myt.presentation.main.screen_new_plan.components.DateTimePickerField
import com.ruskaof.myt.presentation.theme.AppTheme

@Composable
fun NewPlanScreen(
    viewModel: NewMainScreenViewModel = hiltViewModel(),
    navController: NavController,
    context: Context
) {
    var writePlanName by remember {
        mutableStateOf("")
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.writePlan(writePlanName, viewModel.startTimeState.value, viewModel.endTimeState.value)
                navController.popBackStack()
            }) {
                Icon(Icons.Filled.Check, "Add")
            }
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            OutlinedTextField(
                value = writePlanName,
                onValueChange = { writePlanName = it },
                singleLine = true,
                label = { Text("Name") },
            )
            DateTimePickerField(
                context = context,
                textStyle = AppTheme.typography.makingPlanDataStyle,
                label = "start",
                setTime = {viewModel.setStartTime(it)}
                )
            DateTimePickerField(
                context = context,
                textStyle = AppTheme.typography.makingPlanDataStyle,
                label = "end",
                setTime = {viewModel.setEndTime(it)}
                )


        }
    }




}