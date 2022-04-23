package com.ruskaof.myt.presentation.main.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ruskaof.myt.presentation.common.BOTTOM_NAVIGATION_HEIGHT

@Composable
fun BottomNavigationBar(
    backgroundColor: Color,
    contentColor: Color,
    navController: NavController
) {
    val items = listOf(
        BottomNavigationItem.Schedule,
        BottomNavigationItem.Menu
    )

    BottomNavigation(
        backgroundColor = backgroundColor,
        modifier = Modifier.height(BOTTOM_NAVIGATION_HEIGHT)
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
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(it.route)
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(
        backgroundColor = Color.Red,
        contentColor = Color.Blue,
        rememberNavController()
    )
}