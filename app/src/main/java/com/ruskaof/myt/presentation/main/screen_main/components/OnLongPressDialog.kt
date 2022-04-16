package com.ruskaof.myt.presentation.main.screen_main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ruskaof.myt.presentation.theme.AppTheme

@Composable
private fun OnLongPressDialogUI(
    dialogIsOpen: MutableState<Boolean>,
    onOk: () -> Unit,
    onCancel: () -> Unit,

    ) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
        elevation = 8.dp,
    ) {
        Column(
            Modifier.background(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Are you sure you want to delete this plan?",
                    style = TextStyle(
                        color = AppTheme.colors.primaryTextColor,
                        fontSize = 27.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Light
                    )
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(AppTheme.colors.secondary),
            ) {
                TextButton(modifier = Modifier.weight(1f), onClick = {
                    onCancel()
                    dialogIsOpen.value = false
                }) {
                    Text(
                        "Cancel",
                        fontWeight = FontWeight.Light,
                        color = AppTheme.colors.primaryTextColor,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                        fontSize = 20.sp
                    )
                }
                TextButton(modifier = Modifier.weight(1f), onClick = {
                    onOk()
                    dialogIsOpen.value = false
                }) {
                    Text(
                        "Ok",
                        fontWeight = FontWeight.Light,
                        color = AppTheme.colors.primaryTextColor,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Composable
fun OnLongPressDialog(
    openDialogCustom: MutableState<Boolean>,
    onOk: () -> Unit,
    onCancel: () -> Unit
) {
    Dialog(onDismissRequest = { openDialogCustom.value = false }) {
        OnLongPressDialogUI(dialogIsOpen = openDialogCustom, onOk = onOk, onCancel = onCancel)
    }
}
