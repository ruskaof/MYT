package com.ruskaof.myt.presentation.main.screen_new_plan.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruskaof.myt.presentation.theme.AppTheme

@Composable
fun Counter(
    value: MutableState<Int>,
    max: Int,
    min: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "${value.value.toString()} times",
            style = TextStyle(
                color = AppTheme.colors.primaryTextColor,
                fontSize = 20.sp
            )
        )


        Row {
            Icon(Icons.Sharp.KeyboardArrowUp, contentDescription = null, modifier = Modifier
                .clickable { if (value.value + 1 <= max) value.value++ }
                .size(60.dp))

            Icon(Icons.Rounded.KeyboardArrowDown, contentDescription = null, modifier = Modifier
                .clickable { if (value.value - 1 >= min) value.value-- }
                .size(60.dp))
        }

    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun CounterPreview() {
    Counter(value = mutableStateOf(1), max = Int.MAX_VALUE, min = Int.MIN_VALUE)
}