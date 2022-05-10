@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.ruskaof.myt.presentation.main.navigation

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ruskaof.myt.presentation.Screen
import com.ruskaof.myt.presentation.main.screen_main.MainScreen
import com.ruskaof.myt.presentation.main.screen_menu.screen_archieve.ArchiveScreen
import com.ruskaof.myt.presentation.main.screen_menu.screen_pomodoro.PomodoroScreen
import com.ruskaof.myt.presentation.main.screen_menu.screen_settings.SettingsScreen
import com.ruskaof.myt.presentation.main.screen_menu.screen_statistics.StatisticsScreen
import com.ruskaof.myt.presentation.main.screen_new_plan.NewPlanScreen
import com.ruskaof.myt.presentation.main.screen_splash.AnimatedSplashScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationComponent(
    navController: NavHostController,
    context: Context,
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
//            enterTransition = { _, _ -> enterTransitionAnimation() },
//            exitTransition = { _, _ -> exitTransitionAnimation() }
        ) {
            NewPlanScreen(context = context, navController = navController)
        }

        composable(
            route = Screen.ArchiveScreen.route
        ) {
            ArchiveScreen(context = context)
        }

        composable(
            route = Screen.SettingsScreen.route
        ) {
            SettingsScreen()
        }

        composable(
            route = Screen.StatisticsScreen.route
        ) {
            StatisticsScreen()
        }

        composable(
            route = Screen.PomodoroScreen.route
        ) {
            PomodoroScreen()
        }
    }
}