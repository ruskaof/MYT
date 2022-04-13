package com.ruskaof.myt.presentation.main.screen_main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ruskaof.myt.presentation.Screen
import com.ruskaof.myt.presentation.main.screen_main.components.PlanListItem

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    viewModel.getAllPlans()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.NewPlanScreen.route) }) {
                Icon(Icons.Filled.Add, "New")
            }
        }
    ) {
        LazyColumn() {
            items(viewModel.plansState.value.plans) {
                PlanListItem(plan = it, onClick = { id ->
                    viewModel.removePlan(id)
                    viewModel.getAllPlans()
                })
            }
        }
    }
}