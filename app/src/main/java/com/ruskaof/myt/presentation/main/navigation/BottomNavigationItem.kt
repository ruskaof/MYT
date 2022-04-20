package com.ruskaof.myt.presentation.main.navigation

import com.ruskaof.myt.R
import com.ruskaof.myt.presentation.Screen

sealed class BottomNavigationItem(
    val route: String,
    val icon: Int,
    val title: String
) {
    object Schedule :
        BottomNavigationItem(
            Screen.MainScreen.route,
            R.drawable.ic_baseline_access_time_filled_24,
            "Schedule"
        )

    object Menu : BottomNavigationItem(
        Screen.MenuScreen.route,
        R.drawable.ic_baseline_menu_24,
        "Menu"
    )

}
