package com.ruskaof.myt.presentation.main.bottom_navigation

import com.ruskaof.myt.R

sealed class NavigationItem(
    val route: String,
    val icon: Int,
    val title: String
) {
    object Schedule :
        NavigationItem("schedule", R.drawable.ic_baseline_access_time_filled_24, "Schedule")

    object Menu : NavigationItem("menu", R.drawable.ic_baseline_menu_24, "Menu")
}
