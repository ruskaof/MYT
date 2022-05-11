@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.ruskaof.myt.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ruskaof.myt.presentation.Screen
import com.ruskaof.myt.presentation.screens.screen_main.MainScreen
import com.ruskaof.myt.presentation.screens.screen_menu.screen_archieve.ArchiveScreen
import com.ruskaof.myt.presentation.screens.screen_menu.screen_pomodoro.PomodoroScreen
import com.ruskaof.myt.presentation.screens.screen_menu.screen_settings.SettingsScreen
import com.ruskaof.myt.presentation.screens.screen_menu.screen_statistics.StatisticsScreen
import com.ruskaof.myt.presentation.screens.screen_new_plan.NewPlanScreen
import com.ruskaof.myt.presentation.screens.screen_splash.AnimatedSplashScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationComponent(
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        startDestination = Screen.AnimatedSplashScreen.route
    ) {

        composable(
            route = Screen.AnimatedSplashScreen.route,
        ) {
            AnimatedSplashScreen(navController)
        }

        composable(Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }


        composable(
            route = Screen.NewPlanScreen.route,
        ) {
            NewPlanScreen(context = LocalContext.current, navController = navController)
        }

        composable(
            route = Screen.ArchiveScreen.route
        ) {
            ArchiveScreen(context = LocalContext.current, navController = navController)
        }

        composable(
            route = Screen.SettingsScreen.route
        ) {
            SettingsScreen()
        }

        composable(
            route = Screen.StatisticsScreen.route
        ) {
            StatisticsScreen(navController = navController)
        }

        composable(
            route = Screen.PomodoroScreen.route
        ) {
            PomodoroScreen(context = LocalContext.current, navController = navController)
        }
    }
}