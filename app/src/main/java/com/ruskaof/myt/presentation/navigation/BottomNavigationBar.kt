package com.ruskaof.myt.presentation.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    backgroundColor: Color,
    contentColor: Color,
    navController: NavController,
    topBarText: MutableState<String>
) {
    val items = listOf(
        BottomNavigationItem.Schedule,
        BottomNavigationItem.Menu
    )

    BottomNavigation(
        backgroundColor = backgroundColor,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach {
            BottomNavigationItem(
                selected = currentRoute == it.route,
                alwaysShowLabel = true,
                selectedContentColor = contentColor,
                unselectedContentColor = contentColor.copy(alpha = 0.4f),
                icon = { Icon(painter = painterResource(id = it.icon), contentDescription = null) },
                label = { Text(it.title) },
                onClick = {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    topBarText.value = it.title
                }
            )
        }
    }
}

//@Preview
//@Composable
//fun BottomNavigationBarPreview() {
//    BottomNavigationBar(
//        backgroundColor = Color.Red,
//        contentColor = Color.Blue,
//        rememberNavController(),
//
//    )
//}