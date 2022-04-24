package com.ruskaof.myt.presentation.main.screen_new_plan.components

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ruskaof.myt.presentation.theme.AppTheme
import java.time.LocalDateTime

@Composable
fun DatePickerField(
    context: Context,
    label: String,
    setDate: (year: Int, month: Int, day: Int) -> Unit,
    time: LocalDateTime,
    timeSurfaceColor: Color,
    primaryTextColor: Color = AppTheme.colors.primaryTextColor
) {
    val selectedDate =
        "${time.dayOfMonth}/${time.monthValue}/${time.year}"

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, newYear: Int, newMonth: Int, newDayOfMonth: Int ->
            setDate(newYear, newMonth + 1, newDayOfMonth)
            Log.d("PICKER", "DateTimePickerField: changed")
        }, time.year, time.monthValue - 1, time.dayOfMonth
    )

    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = TextStyle(
                color = primaryTextColor,
                fontSize = 30.sp,
                fontWeight = FontWeight.Light
            ),
            modifier = Modifier.weight(2f),
            textAlign = TextAlign.Center
        )

        Surface(
            modifier = Modifier.weight(4f),
            color = timeSurfaceColor,
            shape = RoundedCornerShape(30)
        ) {
            Column(modifier = Modifier.weight(4f)) {
                Text(text = selectedDate, style = TextStyle(
                    color = primaryTextColor,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Light
                ), modifier = Modifier
                    .clickable {
                        datePickerDialog.show()
                    }
                    .fillMaxWidth(), textAlign = TextAlign.Center
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DatePickerFieldPreview() {
    DatePickerField(
        context = LocalContext.current,
        label = "Label",
        setDate = { _, _, _ -> },
        time = LocalDateTime.now(),
        timeSurfaceColor = Color(0xFFB3E5FC),
        primaryTextColor = Color.Black
    )
}