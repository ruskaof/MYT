package com.ruskaof.myt.presentation

import android.content.Context
import android.util.Log
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.ruskaof.myt.presentation.animation.enterTransitionAnimation
import com.ruskaof.myt.presentation.animation.exitTransitionAnimation
import com.ruskaof.myt.presentation.main.navigation.BottomNavigationItem
import com.ruskaof.myt.presentation.main.screen_main.MainScreen
import com.ruskaof.myt.presentation.main.screen_menu.MenuScreen
import com.ruskaof.myt.presentation.main.screen_new_plan.NewPlanScreen
import com.ruskaof.myt.presentation.main.screen_splash.AnimatedSplashScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationComponent(
    navController: NavHostController,
    context: Context
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.AnimatedSplashScreen.route
    ) {

        composable(
            route = Screen.AnimatedSplashScreen.route,
        ) {
            AnimatedSplashScreen(navController)
        }

        composable(
            route = Screen.MainScreen.route,
            enterTransition = { initial, target ->
                Log.d(
                    "MAIN_TAG",
                    "NavigationComponent main screen enter: initial: ${initial.destination.route} target: ${target.destination.route}"
                )
                if (initial.destination.route == BottomNavigationItem.Menu.route ||
                    initial.destination.route == BottomNavigationItem.Schedule.route
                ) {
                    EnterTransition.None
                } else {
                    enterTransitionAnimation()
                }
            },
            exitTransition = { initial, target ->
                Log.d(
                    "MAIN_TAG",
                    "NavigationComponent main screen exit: initial: ${initial.destination.route} target: ${target.destination.route}"
                )
                if (target.destination.route == BottomNavigationItem.Menu.route ||
                    target.destination.route == BottomNavigationItem.Schedule.route
                ) {
                    ExitTransition.None
                } else {
                    exitTransitionAnimation()
                }
            }
        ) {
            MainScreen(navController = navController)
        }


        composable(
            route = Screen.MenuScreen.route,
            enterTransition = { initial, target ->
                if (initial.destination.route == BottomNavigationItem.Menu.route ||
                    initial.destination.route == BottomNavigationItem.Schedule.route
                ) {
                    EnterTransition.None
                } else {
                    enterTransitionAnimation()
                }
            },
            exitTransition = { initial, target ->
                if (target.destination.route == BottomNavigationItem.Menu.route ||
                    target.destination.route == BottomNavigationItem.Schedule.route
                ) {
                    ExitTransition.None
                } else {
                    exitTransitionAnimation()
                }
            }
        ) {
            MenuScreen(navController = navController)
        }

        composable(
            route = Screen.NewPlanScreen.route,
            enterTransition = { _, _ -> enterTransitionAnimation() },
            exitTransition = { _, _ -> exitTransitionAnimation() }
        ) {
            NewPlanScreen(context = context, navController = navController)
        }
    }
}