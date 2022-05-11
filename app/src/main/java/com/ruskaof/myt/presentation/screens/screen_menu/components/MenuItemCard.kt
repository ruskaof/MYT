package com.ruskaof.myt.presentation.screens.screen_menu.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruskaof.myt.presentation.theme.AppTheme

@Composable
fun MenuItemCard(
    size: Dp = 150.dp,
    icon: ImageVector,
    iconColor: Color,
    onClick: () -> Unit,
    label: String
) {
    Surface(
        modifier = Modifier
            .padding(AppTheme.shapes.defaultPaddingFromStart)
            .size(size),
        shape = RoundedCornerShape(10),
        elevation = 5.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true, color = AppTheme.colors.primary)
                ) {
                    onClick()
                }
        ) {
            // TODO: make a good design with an icon
//            Icon(
//                imageVector = icon,
//                contentDescription = null,
//                tint = iconColor,
//                modifier = Modifier.size(size = size / 1f)
//            )
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = label,
                    style = TextStyle(
                        color = AppTheme.colors.primaryTextColor,
                        fontSize = 30.sp,
                    ),
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
fun MenuItemCardPreview() {
    MenuItemCard(icon = Icons.Default.Star, iconColor = Color.Black, onClick = {}, label = "label")
}