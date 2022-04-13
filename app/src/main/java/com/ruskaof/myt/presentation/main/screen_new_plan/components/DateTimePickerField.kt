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
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.ruskaof.myt.common.niceTime
import java.time.LocalDateTime
import java.util.*

@Composable
fun DateTimePickerField(
    context: Context,
    textStyle: TextStyle,
    label: String,
    setTime: (LocalDateTime) -> Unit,
) {
    val calendar = Calendar.getInstance()

    var year = calendar.get(Calendar.YEAR)
    var month = calendar.get(Calendar.MONTH)
    var day = calendar.get(Calendar.DAY_OF_MONTH)

    var hour = calendar.get(Calendar.HOUR_OF_DAY)
    var minute = calendar.get(Calendar.MINUTE)

    var selectedDate by remember {
        mutableStateOf("$day/$month/$year")
    }
    var selectedTime by remember {
        mutableStateOf(niceTime(hour, minute))
    }

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, newYear: Int, newMonth: Int, newDayOfMonth: Int ->
            selectedDate = "$newDayOfMonth/$newMonth/$newYear"
            year = newYear
            month = newMonth
            day = newDayOfMonth
            Log.d("PICKER", "DateTimePickerField: changed")
            setTime(LocalDateTime.of(year, month, day, hour, minute))
        }, year, month, day
    )
    val timePickerDialog = TimePickerDialog(
        context,
        { _, newHour: Int, newMinute: Int ->
            selectedTime = niceTime(newHour, newMinute)
            hour = newHour
            minute = newMinute
            Log.d("PICKER", "DateTimePickerField: changed")
            setTime(LocalDateTime.of(year, month, day, hour, minute))
        }, hour, minute, true
    )

    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, style = textStyle, modifier = Modifier.weight(2f))
        Column(modifier = Modifier.weight(4f)) {
            Text(text = selectedDate, style = textStyle, modifier = Modifier
                .clickable {
                    datePickerDialog.show()
                }
            )
            Text(text = selectedTime, style = textStyle, modifier = Modifier
                .clickable {
                    timePickerDialog.show()
                })
        }
    }
}