package com.ruskaof.myt.presentation.main.screen_main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ruskaof.myt.common.niceTime
import com.ruskaof.myt.domain.model.Plan
import java.time.LocalDateTime

@Composable
fun PlanListItem(
    plan: Plan = Plan(
        "Example name",
        startTime = LocalDateTime.of(2022, 9, 20, 13, 34),
        endTime = LocalDateTime.of(2022, 9, 20, 15, 4),
        id = 5
    ),
    startTimeStyle: TextStyle = TextStyle(),
    endTimeStyle: TextStyle = TextStyle(),
    planNameTextStyle: TextStyle = TextStyle(color = Color.White),
    onClick: (Int) -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxWidth().clickable {
            onClick(plan.id)
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                modifier = Modifier.padding(end = 5.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(niceTime(plan.startTime), style = startTimeStyle)
                Text(niceTime(plan.endTime), style = endTimeStyle)
            }
            TextInOval(text = plan.name, ovalColor = Color.Blue, style = planNameTextStyle)
        }
    }
}



@Preview (showBackground = true)
@Composable
fun PlanListItemPreview() {
    PlanListItem()
}