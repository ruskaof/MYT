package com.ruskaof.myt.presentation.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.ruskaof.myt.presentation.theme.AppTypography

val lightTextTypography = AppTypography(
    primaryTextStyle = TextStyle(),
    makingPlanDataStyle = TextStyle(
        color = Color.Black,
        fontSize = 30.sp
    )
)

val darkTextTypography = AppTypography(
    primaryTextStyle = TextStyle(),
    makingPlanDataStyle = TextStyle(
        color = Color.Black,
        fontSize = 14.sp
    )
)