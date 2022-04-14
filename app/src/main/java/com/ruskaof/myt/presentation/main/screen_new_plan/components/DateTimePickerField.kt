package com.ruskaof.myt.presentation.main.screen_new_plan.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.ruskaof.myt.common.niceTime
import java.time.LocalDateTime

@Composable
fun DateTimePickerField(
    context: Context,
    textStyle: TextStyle,
    label: String,
    setTime: (hour: Int, minute: Int) -> Unit,
    setDate: (year: Int, month: Int, day: Int) -> Unit,
    time: LocalDateTime,
    timeSurfaceColor: Color
) {

    val selectedDate =
        "${time.dayOfMonth}/${time.monthValue}/${time.year}"

    val selectedTime =
        niceTime(time.hour, time.minute)

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, newYear: Int, newMonth: Int, newDayOfMonth: Int ->
            setDate(newYear, newMonth + 1, newDayOfMonth)
            Log.d("PICKER", "DateTimePickerField: changed")
        }, time.year, time.monthValue - 1, time.dayOfMonth
    )
    val timePickerDialog = TimePickerDialog(
        context,
        { _, newHour: Int, newMinute: Int ->
            setTime(newHour, newMinute)
        }, time.hour, time.minute, true
    )

    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = textStyle,
            modifier = Modifier.weight(2f),
            textAlign = TextAlign.Center
        )

        Surface(
            modifier = Modifier.weight(4f),
            color = timeSurfaceColor,
            shape = RoundedCornerShape(30)
        ) {
            Column(modifier = Modifier.weight(4f)) {
                Text(text = selectedDate, style = textStyle, modifier = Modifier
                    .clickable {
                        datePickerDialog.show()
                    }
                    .fillMaxWidth(), textAlign = TextAlign.Center
                )
                Text(text = selectedTime, style = textStyle, modifier = Modifier
                    .clickable {
                        timePickerDialog.show()
                    }
                    .fillMaxWidth(), textAlign = TextAlign.Center
                )
            }
        }
    }


}