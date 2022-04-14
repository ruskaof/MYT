package com.ruskaof.myt.presentation.main.screen_main.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TextInOval(
    text: String = "Some text",
    ovalColor: Color = Color.Blue,
    style: TextStyle = TextStyle(color = Color.White),
) {
    Surface(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(5.dp),
        shape = RoundedCornerShape(50),
        color = ovalColor,
        elevation = 5.dp
    ) {
        Text(text, modifier = Modifier.padding(10.dp), style = style)
    }
}

@Preview (showBackground = true)
@Composable
fun TextInOvalPreview() {
    TextInOval()
}