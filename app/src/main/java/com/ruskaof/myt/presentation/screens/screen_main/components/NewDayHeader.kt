package com.ruskaof.myt.presentation.screens.screen_main.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruskaof.myt.presentation.theme.AppTheme
import java.time.LocalDateTime
import java.util.*

@Composable
fun NewDayHeader(
    localDateTime: LocalDateTime = LocalDateTime.now(),
    today: Boolean = false,
    primaryTextColor: Color = AppTheme.colors.primaryTextColor,
    perceptibleColoredTextColor: Color = AppTheme.colors.perceptibleColoredTextColor,
    paddingFromStart: Dp = AppTheme.shapes.defaultPaddingFromStart
) {
    Column(
        modifier = Modifier.padding(start = paddingFromStart)
    ) {
        Text(
            localDateTime.dayOfWeek.toString().lowercase()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            style = TextStyle(
                color = perceptibleColoredTextColor,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
            )
        )
        Row {
            Text(
                text = localDateTime.dayOfMonth.toString() + " " + localDateTime.month.name.lowercase()
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                style = TextStyle(
                    color = primaryTextColor.copy(alpha = 0.5f),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light
                )
            )
            if (today) {
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = "(Today)",
                    style = TextStyle(
                        color = primaryTextColor.copy(alpha = 0.5f),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewDateTimePreview() {
    NewDayHeader(
        primaryTextColor = Color.Black,
        perceptibleColoredTextColor = Color(0xFFAB47BC),
        paddingFromStart = 15.dp
    )
}