package com.ruskaof.myt.presentation.main.bottom_navigation

import com.ruskaof.myt.R
import com.ruskaof.myt.presentation.Screen

sealed class NavigationItem(
    val route: String,
    val icon: Int,
    val title: String
) {
    object Schedule :
        NavigationItem(
            Screen.MainScreen.route,
            R.drawable.ic_baseline_access_time_filled_24,
            "Schedule"
        )

    object Menu : NavigationItem(
        Screen.MenuScreen.route,
        R.drawable.ic_baseline_menu_24,
        "Menu"
    ) // TODO: make a menu screen
}
