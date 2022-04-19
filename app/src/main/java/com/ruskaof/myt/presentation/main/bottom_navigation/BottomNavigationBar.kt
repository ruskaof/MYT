package com.ruskaof.myt.presentation.main.bottom_navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BottomNavigationBar(
    backgroundColor: Color,
    contentColor: Color
) {
    val items = listOf(
        NavigationItem.Schedule,
        NavigationItem.Menu
    )

    BottomNavigation(
        backgroundColor = backgroundColor,
    ) {
        items.forEach {
            BottomNavigationItem(
                selected = false,
                alwaysShowLabel = true,
                selectedContentColor = contentColor,
                unselectedContentColor = contentColor.copy(alpha = 0.4f),
                icon = { Icon(painter = painterResource(id = it.icon), contentDescription = null) },
                label = { Text(it.title) },
                onClick = { /*TODO*/ }
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(backgroundColor = Color.Red, contentColor = Color.Blue)
}