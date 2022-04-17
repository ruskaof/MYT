package com.ruskaof.myt.presentation.main.screen_splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ruskaof.myt.presentation.Screen
import com.ruskaof.myt.presentation.theme.AppTheme
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(
    navController: NavController
) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(1500)
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(1700)
        navController.popBackStack()
        navController.navigate(Screen.MainScreen.route)
    }
    SplashScreen(alphaAnim.value)
}

@Composable
private fun SplashScreen(
    alpha: Float
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.primaryBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "MYT",
                style = TextStyle(
                    color = AppTheme.colors.perceptibleColoredTextColor,
                    fontSize = 50.sp
                ),
                fontWeight = FontWeight.Light,
                modifier = Modifier.alpha(alpha),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Manage Your Time",
                style = TextStyle(color = AppTheme.colors.primaryTextColor, fontSize = 20.sp),
                fontWeight = FontWeight.Light,
                modifier = Modifier.alpha(alpha),
                textAlign = TextAlign.Center
            )
        }
    }
}