package com.ruskaof.myt.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ruskaof.myt.presentation.main.screen_main.MainScreen
import com.ruskaof.myt.presentation.main.screen_menu.MenuScreen
import com.ruskaof.myt.presentation.main.screen_new_plan.NewPlanScreen
import com.ruskaof.myt.presentation.main.screen_splash.AnimatedSplashScreen
import com.ruskaof.myt.presentation.theme.MainTheme
import com.ruskaof.myt.presentation.theme.darkColorPallet
import com.ruskaof.myt.presentation.theme.lightColorPallet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val screenChangeDuration = 200

        setContent {
            val context = this
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

            val navController = rememberNavController()

            MainTheme(
                darkTheme = isDarkMode.value
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Screen.AnimatedSplashScreen.route
                ) {

                    composable(
                        route = Screen.AnimatedSplashScreen.route
                    ) {
                        AnimatedSplashScreen(navController)
                    }

                    composable(
                        route = Screen.MainScreen.route,
                    ) {
                        MainScreen(navController = navController)
                    }

                    composable(
                        route = Screen.NewPlanScreen.route,
                    ) {
                        NewPlanScreen(context = context, navController = navController)
                    }

                    composable(
                        route = Screen.MenuScreen.route
                    ) {
                        MenuScreen(navController = navController)
                    }
                }
            }
        }
    }
}