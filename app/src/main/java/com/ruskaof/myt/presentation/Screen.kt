package com.ruskaof.myt.presentation

sealed class Screen(val route: String) {

    object MainScreen : Screen("main_screen")

    object ScheduleScreen : Screen("schedule_screen")

    object NewPlanScreen : Screen("new_plan_screen")

    object AnimatedSplashScreen : Screen("splash_screen")

    object MenuScreen : Screen("menu_screen")
    object ArchiveScreen : Screen("archive_screen")
    object SettingsScreen : Screen("settings_screen")
    object StatisticsScreen : Screen("statistics_screen")
    object PomodoroScreen : Screen("pomodoro_screen")
}