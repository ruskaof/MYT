package com.ruskaof.myt.presentation.main.screen_menu

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ruskaof.myt.presentation.Screen
import com.ruskaof.myt.presentation.main.navigation.BottomNavigationBar
import com.ruskaof.myt.presentation.main.screen_menu.components.MenuItemCard
import com.ruskaof.myt.presentation.main.screen_new_plan.components.TopBar
import com.ruskaof.myt.presentation.theme.AppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MenuScreen(
    viewModel: MenuScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopBar(
                text = "Menu"
            )
        },
        backgroundColor = AppTheme.colors.primaryBackground,
        bottomBar = {
            BottomNavigationBar(
                backgroundColor = AppTheme.colors.primary,
                contentColor = AppTheme.colors.secondary,
                navController = navController
            )
        }
    ) {

        CompositionLocalProvider(
            LocalOverScrollConfiguration provides null // Disabling overscroll animation
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                MenuItemCard(
                    icon = Icons.Default.Info,
                    iconColor = AppTheme.colors.secondary,
                    onClick = { navController.navigate(Screen.ArchiveScreen.route) },
                    label = "Archive"
                )

                MenuItemCard(
                    icon = Icons.Default.Settings,
                    iconColor = AppTheme.colors.secondary,
                    onClick = { navController.navigate(Screen.SettingsScreen.route) },
                    label = "Settings"
                )

                MenuItemCard(
                    icon = Icons.Default.Star,
                    iconColor = AppTheme.colors.secondary,
                    onClick = { navController.navigate(Screen.StatisticsScreen.route) },
                    label = "Stats"
                )
            }
        }
    }
}