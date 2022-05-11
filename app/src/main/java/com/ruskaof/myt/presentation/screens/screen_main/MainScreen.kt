@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.ruskaof.myt.presentation.screens.screen_main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ruskaof.myt.presentation.Screen
import com.ruskaof.myt.presentation.navigation.BottomNavigationBar
import com.ruskaof.myt.presentation.screens.screen_menu.MenuScreen
import com.ruskaof.myt.presentation.screens.screen_new_plan.components.TopBar
import com.ruskaof.myt.presentation.screens.screen_schedule.ScheduleScreen
import com.ruskaof.myt.presentation.theme.AppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    navController: NavController,
) {
    val bottomNavController = rememberNavController()
    val topBarText = remember {
        mutableStateOf("Schedule")
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.NewPlanScreen.route) },
                backgroundColor = AppTheme.colors.secondary
            ) {
                Icon(Icons.Filled.Add, "New")
            }
        },
        topBar = {
            TopBar(
                text = topBarText.value
            )
        },
        backgroundColor = AppTheme.colors.primaryBackground,
        bottomBar = {
            BottomNavigationBar(
                backgroundColor = AppTheme.colors.primary,
                contentColor = AppTheme.colors.secondary,
                navController = bottomNavController,
                topBarText = topBarText
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = Screen.ScheduleScreen.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.ScheduleScreen.route) { ScheduleScreen(navController = navController) }
            composable(Screen.MenuScreen.route) { MenuScreen(navController = navController) }
        }
    }
}
