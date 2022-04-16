package com.ruskaof.myt.presentation

import CustomEasing
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ruskaof.myt.presentation.main.screen_main.MainScreen
import com.ruskaof.myt.presentation.main.screen_new_plan.NewPlanScreen
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
            val context = this
            val isDarkModeValue = isSystemInDarkTheme()
            val isDarkMode = remember { mutableStateOf(isDarkModeValue) }
            val systemUiController = rememberSystemUiController()

            SideEffect {
                systemUiController.setNavigationBarColor(
                    color = if (isDarkMode.value) darkColorPallet.primaryBackground else lightColorPallet.primaryBackground
                )
                systemUiController.setStatusBarColor(
                    color = if (isDarkMode.value) darkColorPallet.primaryBackground else lightColorPallet.primaryBackground
                )
            }

            val navController = rememberAnimatedNavController()
            MainTheme(
                darkTheme = isDarkMode.value
            ) {
                AnimatedNavHost(
                    navController = navController,
                    startDestination = Screen.MainScreen.route
                ) {
                    composable(
                        route = Screen.MainScreen.route,
                        enterTransition = { _, _ ->
                            slideInHorizontally(
                                initialOffsetX = { it }, // small slide 300px
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = CustomEasing // interpolator
                                )
                            )
                        },
                    )
                    {
                        MainScreen(navController = navController)
                    }

                    composable(
                        route = Screen.NewPlanScreen.route,
                        enterTransition = { _, _ ->
                            slideInHorizontally(
                                initialOffsetX = { it }, // small slide 300px
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = CustomEasing // interpolator
                                )
                            )
                        },
                        popExitTransition = { _, _ ->
                            slideOutHorizontally(
                                // small slide 300px
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = CustomEasing // interpolator
                                )
                            )
                        },
                    )
                    {
                        NewPlanScreen(context = context, navController = navController)
                    }
                }
            }
        }
    }
}