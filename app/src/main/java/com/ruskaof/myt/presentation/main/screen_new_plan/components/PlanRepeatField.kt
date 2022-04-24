package com.ruskaof.myt.presentation.main.screen_new_plan.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruskaof.myt.presentation.theme.AppTheme

@Composable
fun PlanRepeatField(
    checked: MutableState<Boolean>,
    selectedPeriod: MutableState<Period>,
    timesValue: MutableState<Int>
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(top = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Repeat",
                style = TextStyle(
                    color = AppTheme.colors.primaryTextColor,
                    fontSize = 30.sp
                ),
                modifier = Modifier.padding(end = 10.dp)
            )

            Checkbox(checked = checked.value, onCheckedChange = { checked.value = !checked.value })
        }

        if (checked.value) {
            SelectPeriodButtons(selectedState = selectedPeriod, timesValue = timesValue)
        }
    }
}

@Composable
fun SelectPeriodButtons(
    selectedState: MutableState<Period>,
    timesValue: MutableState<Int>
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        color = AppTheme.colors.secondary
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedState.value == Period.DAY,
                    onClick = { selectedState.value = Period.DAY },
                )
                Text(
                    "Every day", style = TextStyle(
                        color = AppTheme.colors.primaryTextColor,
                        fontSize = 20.sp
                    )
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedState.value == Period.WEEK,
                    onClick = { selectedState.value = Period.WEEK },
                )
                Text(
                    "Every week", style = TextStyle(
                        color = AppTheme.colors.primaryTextColor,
                        fontSize = 20.sp
                    )
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedState.value == Period.TWO_WEEKS,
                    onClick = { selectedState.value = Period.TWO_WEEKS },
                )
                Text(
                    "Every two weeks", style = TextStyle(
                        color = AppTheme.colors.primaryTextColor,
                        fontSize = 20.sp
                    )
                )
            }

            Counter(value = timesValue, max = 100, min = 2)
        }
    }
}

