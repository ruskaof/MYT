package com.ruskaof.myt.presentation.main.screen_main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruskaof.myt.common.niceTime
import com.ruskaof.myt.domain.model.Plan
import com.ruskaof.myt.presentation.theme.AppTheme
import java.time.LocalDateTime

@Composable
fun PlanListItem(
    plan: Plan = Plan(
        "Example name",
        startTime = LocalDateTime.of(2022, 9, 20, 13, 34),
        endTime = LocalDateTime.of(2022, 9, 20, 15, 4),
        id = 5
    ),
    startTimeStyle: TextStyle = TextStyle(
        color = Color.Black,
        fontSize = 25.sp,
        fontWeight = FontWeight.Light
    ),
    endTimeStyle: TextStyle = TextStyle(
        color = Color.Black.copy(alpha = 0.7f),
        fontSize = 20.sp,
        fontWeight = FontWeight.Light
    ),
    planNameTextStyle: TextStyle = TextStyle(color = Color.White),
    paddingStart: Dp,
    onClick: (Int) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(plan.id)
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                modifier = Modifier
                    .padding(start = paddingStart, end = 5.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(niceTime(plan.startTime), style = startTimeStyle)
                Text(niceTime(plan.endTime), style = endTimeStyle)
            }
            Box(
            ) {
                TextInOval(
                    text = plan.name,
                    ovalColor = AppTheme.colors.primary,
                    style = planNameTextStyle
                )
            }
        }
    }
}



@Preview (showBackground = true)
@Composable
fun PlanListItemPreview() {
    PlanListItem(paddingStart = 15.dp)
}