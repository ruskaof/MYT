package com.ruskaof.myt.presentation.main.screen_menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ruskaof.myt.presentation.main.navigation.BottomNavigationBar
import com.ruskaof.myt.presentation.main.screen_new_plan.components.TopBar
import com.ruskaof.myt.presentation.theme.AppTheme

@Composable
fun MenuScreen(
    viewModel: MenuScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopBar(
                text = "Schedule"
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
        Box(modifier = Modifier.fillMaxSize()) {
            Text("menu")
        }
    }
}