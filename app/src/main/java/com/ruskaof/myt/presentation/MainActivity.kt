package com.ruskaof.myt.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ruskaof.myt.common.Constants
import com.ruskaof.myt.presentation.navigation.NavigationComponent
import com.ruskaof.myt.presentation.notifications.createNotificationChannel
import com.ruskaof.myt.presentation.theme.AppTheme
import com.ruskaof.myt.presentation.theme.MainTheme
import com.ruskaof.myt.presentation.theme.darkColorPallet
import com.ruskaof.myt.presentation.theme.lightColorPallet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isDarkModeValue = isSystemInDarkTheme()
            val isDarkMode = remember { mutableStateOf(isDarkModeValue) }
            val systemUiController = rememberSystemUiController()

            SideEffect {
                systemUiController.setNavigationBarColor(
                    color = if (isDarkMode.value) darkColorPallet.primary else lightColorPallet.primary
                )
                systemUiController.setStatusBarColor(
                    color = if (isDarkMode.value) darkColorPallet.primaryBackground else lightColorPallet.primaryBackground
                )
            }

            LaunchedEffect(true) {
                createNotificationChannel(Constants.NOTIFICATION_CHANNEL_ID, this@MainActivity)
            }


            val navController: NavHostController = rememberNavController()
            MainTheme(
                darkTheme = isDarkMode.value
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(AppTheme.colors.primaryBackground)
                ) {
                    NavigationComponent(navController = navController)
                }
            }
        }
    }
}