package com.ruskaof.myt.presentation

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object NewPlanScreen : Screen("new_plan_screen")
    object AnimatedSplashScreen : Screen("splash_screen")
}