package com.ruskaof.myt.presentation.main.screen_main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ruskaof.myt.presentation.theme.AppTheme
import java.time.LocalDateTime
import java.util.*

@Composable
fun NewDayHeader(
    localDateTime: LocalDateTime = LocalDateTime.now()
) {
    Column(
        modifier = Modifier.padding(start = AppTheme.shapes.defaultPaddingFromStart)
    ) {
        Text(
            localDateTime.dayOfWeek.toString().lowercase()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            style = TextStyle(
                color = AppTheme.colors.perceptibleColoredTextColor,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
            )
        )
        Text(
            text = localDateTime.dayOfMonth.toString() + " " + localDateTime.month.name.lowercase()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            style = TextStyle(
                color = AppTheme.colors.primaryTextColor.copy(alpha = 0.5f),
                fontSize = 20.sp,
                fontWeight = FontWeight.Light
            )
        )
    }
}

@Preview
@Composable
fun NewDateTimePreview() {
    NewDayHeader()
}