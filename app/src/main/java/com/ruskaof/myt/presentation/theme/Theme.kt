package com.ruskaof.myt.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

data class AppColors(
    val primaryBackground: Color,
)

data class AppShapes(
    val defaultShape: Shape
)

data class AppTypography(
    val primaryTextStyle: TextStyle,
    val makingPlanDataStyle: TextStyle
)


/**
 * https://www.youtube.com/watch?v=SR2AAKRDmQw&list=LL&index=2&t=1154s
 */
object AppTheme{
    val colors: AppColors
        @Composable
        get() = LocalAppColors.current

    val shapes: AppShapes
        @Composable
        get() = LocalAppShapes.current

    val typography: AppTypography
        @Composable
        get() = LocalAppTypography.current


    val LocalAppColors = staticCompositionLocalOf<AppColors> {
        error("No colors provided")
    }
    val LocalAppShapes = staticCompositionLocalOf<AppShapes> {
        error("No shapes provided")
    }
    val LocalAppTypography = staticCompositionLocalOf<AppTypography> {
        error("No typography provided")
    }
}