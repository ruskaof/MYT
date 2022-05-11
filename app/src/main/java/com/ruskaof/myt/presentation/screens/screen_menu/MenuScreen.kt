package com.ruskaof.myt.presentation.screens.screen_menu

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ruskaof.myt.presentation.Screen
import com.ruskaof.myt.presentation.screens.screen_menu.components.MenuItemCard
import com.ruskaof.myt.presentation.theme.AppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MenuScreen(
    viewModel: MenuScreenViewModel = hiltViewModel(),
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxHeight(1 / 2f)
    ) {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            MenuItemCard(
                icon = Icons.Default.Info,
                iconColor = AppTheme.colors.secondary,
                onClick = {
                    navController.navigate(Screen.ArchiveScreen.route) {
                        restoreState = true
                    }
                },
                label = "Archive",
                backgroundColor = AppTheme.colors.primaryBackground,
                modifier = Modifier.weight(1f)
            )

            MenuItemCard(
                icon = Icons.Default.Settings,
                iconColor = AppTheme.colors.secondary,
                onClick = { navController.navigate(Screen.SettingsScreen.route) },
                label = "Settings",
                backgroundColor = AppTheme.colors.primaryBackground,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.weight(1f)
        ) {
            MenuItemCard(
                icon = Icons.Default.Star,
                iconColor = AppTheme.colors.secondary,
                onClick = {
                    navController.navigate(Screen.StatisticsScreen.route) {
                        restoreState = true
                    }
                },
                label = "Stats",
                backgroundColor = AppTheme.colors.primaryBackground,
                modifier = Modifier.weight(1f)
            )

            MenuItemCard(
                icon = Icons.Default.Star,
                iconColor = AppTheme.colors.secondary,
                onClick = {
                    navController.navigate(Screen.PomodoroScreen.route) {
                        restoreState = true
                    }
                },
                label = "Pomodoro",
                backgroundColor = AppTheme.colors.primaryBackground,
                modifier = Modifier.weight(1f)
            )
        }

    }


}