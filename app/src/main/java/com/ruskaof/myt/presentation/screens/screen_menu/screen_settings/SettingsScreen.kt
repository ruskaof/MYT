package com.ruskaof.myt.presentation.screens.screen_menu.screen_settings

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.ruskaof.myt.presentation.screens.screen_new_plan.components.TopBar
import com.ruskaof.myt.presentation.theme.AppTheme

@Composable
fun SettingsScreen(

) {
    Scaffold(
        topBar = { TopBar(text = "Settings") },
        backgroundColor = AppTheme.colors.primaryBackground
    ) {
        // TODO
    }
}