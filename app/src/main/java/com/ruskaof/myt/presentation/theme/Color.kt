package com.ruskaof.myt.presentation.theme

import androidx.compose.ui.graphics.Color

val lightColorPallet = AppColors(
    contrastTextColor = Color.White,
    primaryTextColor = Color.Black,

    primaryBackground = Color.White,
    primary = Color(0xFF5C6BC0),
    secondary = Color(0xFFB3E5FC),
    perceptibleColoredTextColor = Color(0xFFAB47BC)
)

val darkColorPallet = AppColors(
    primaryBackground = Color.Black,
    primaryTextColor = Color.White,
    primary = Color(0xFF673AB7),
    secondary = Color(0xFFB3E5FC),
    contrastTextColor = Color.Black,
    perceptibleColoredTextColor = Color(0xFF2196F3)
)