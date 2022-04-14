package com.ruskaof.myt.presentation.main.screen_new_plan.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.ruskaof.myt.presentation.theme.AppTheme

@Composable
fun TopBar(
    text: String
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.Transparent
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = AppTheme.shapes.defaultPaddingFromStart),
            style = TextStyle(
                color = AppTheme.colors.primaryTextColor,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Start
        )
    }
}