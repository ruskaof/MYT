package com.ruskaof.myt.presentation

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ruskaof.myt.presentation.main.screen_main.MainScreen
import com.ruskaof.myt.presentation.main.screen_menu.MenuScreen
import com.ruskaof.myt.presentation.main.screen_new_plan.NewPlanScreen
import com.ruskaof.myt.presentation.main.screen_splash.AnimatedSplashScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationComponent(
    navController: NavHostController,
    context: Context,
) {
    NavHost(
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
//            enterTransition = { initial, target ->
//                if (initial.destination.route == BottomNavigationItem.Menu.route ||
//                    initial.destination.route == BottomNavigationItem.Schedule.route
//                ) {
//                    EnterTransition.None
//                } else {
//                    enterTransitionAnimation()
//                }
//            },
//            exitTransition = { initial, target ->
//                if (target.destination.route == BottomNavigationItem.Menu.route ||
//                    target.destination.route == BottomNavigationItem.Schedule.route
//                ) {
//                    ExitTransition.None
//                } else {
//                    exitTransitionAnimation()
//                }
//            }
        ) {
            MainScreen(navController = navController)
        }


        composable(
            route = Screen.MenuScreen.route,
//            enterTransition = { initial, target ->
//                if (initial.destination.route == BottomNavigationItem.Menu.route ||
//                    initial.destination.route == BottomNavigationItem.Schedule.route
//                ) {
//                    EnterTransition.None
//                } else {
//                    enterTransitionAnimation()
//                }
//            },
//            exitTransition = { initial, target ->
//                if (target.destination.route == BottomNavigationItem.Menu.route ||
//                    target.destination.route == BottomNavigationItem.Schedule.route
//                ) {
//                    ExitTransition.None
//                } else {
//                    exitTransitionAnimation()
//                }
//            }
        ) {
            MenuScreen(navController = navController)
        }

        composable(
            route = Screen.NewPlanScreen.route,
//            enterTransition = { _, _ -> enterTransitionAnimation() },
//            exitTransition = { _, _ -> exitTransitionAnimation() }
        ) {
            NewPlanScreen(context = context, navController = navController)
        }
    }
}