@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.ruskaof.myt.presentation.screens.screen_main.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlanListItem(
    plan: Plan,
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
    bigPaddingStart: Dp = AppTheme.shapes.bigPaddingFromStart,
    onLongPress: () -> Unit = {},
    primaryColor: Color = AppTheme.colors.primary
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = {},
                onLongClick = { onLongPress() },
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                modifier = Modifier
                    .padding(start = bigPaddingStart, end = 5.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(niceTime(plan.startTime), style = startTimeStyle)
                Text(niceTime(plan.endTime), style = endTimeStyle)
            }
            Box {
                TextInOval(
                    text = plan.name,
                    ovalColor = primaryColor,
                    style = planNameTextStyle
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlanListItemPreview() {
    PlanListItem(
        plan = Plan("Name of plan", LocalDateTime.MIN, LocalDateTime.MAX),
        bigPaddingStart = 40.dp,
        primaryColor = Color(0xFF00897B)
    )
}



