package com.ruskaof.myt.presentation

import android.content.Context
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
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
                if (target.destination.route == BottomNavigationItem.Menu.route &&
                    target.destination.route == BottomNavigationItem.Schedule.route
                ) {
                    slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(300)
                    )
                } else {
                    EnterTransition.None
                }
            },
            exitTransition = { _, _ -> ExitTransition.None }
        ) {
            MainScreen(navController = navController)
        }

        composable(
            route = Screen.NewPlanScreen.route,
        ) {
            NewPlanScreen(context = context, navController = navController)
        }

        composable(
            route = Screen.MenuScreen.route,
            enterTransition = { _, _ -> EnterTransition.None },
            exitTransition = { _, _ -> ExitTransition.None }
        ) {
            MenuScreen(navController = navController)
        }

    }
}