package com.ruskaof.myt.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

data class AppColors(
    val primaryBackground: Color,
    val primaryTextColor: Color,
    val contrastTextColor: Color,
    val perceptibleColoredTextColor: Color,
    val primary: Color,
    val secondary: Color,
    val dialogColor: Color
)

data class AppShapes(
    val defaultShape: Shape,
    val defaultPaddingFromStart: Dp,
    val bigPaddingFromStart: Dp
)

data class AppTypography(
    val int: Int = 1
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