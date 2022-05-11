package com.ruskaof.myt.presentation.screens.screen_main.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ruskaof.myt.presentation.theme.AppTheme

@Composable
private fun OnLongPressDialogUI(
    dialogIsOpen: MutableState<Boolean>,
    onOk: () -> Unit,
    onCancel: () -> Unit,
    primaryTextColor: Color = AppTheme.colors.primaryTextColor,
    secondaryColor: Color = AppTheme.colors.secondary,
    text: String
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
                    .padding(vertical = 30.dp, horizontal = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text,
                    style = TextStyle(
                        color = primaryTextColor,
                        fontSize = 27.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Light
                    )
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(secondaryColor),
            ) {
                TextButton(modifier = Modifier.weight(1f), onClick = {
                    onCancel()
                    dialogIsOpen.value = false
                }) {
                    Text(
                        "Cancel",
                        fontWeight = FontWeight.Normal,
                        color = primaryTextColor,
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
                        fontWeight = FontWeight.Normal,
                        color = primaryTextColor,
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
    onCancel: () -> Unit,
    text: String = "Are you sure you want to delete this plan?"
) {
    Dialog(onDismissRequest = { openDialogCustom.value = false }) {
        OnLongPressDialogUI(
            dialogIsOpen = openDialogCustom,
            onOk = onOk,
            onCancel = onCancel,
            text = text
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
private fun OnLOngPressDialogPreview() {
    OnLongPressDialogUI(
        dialogIsOpen = mutableStateOf(true),
        onOk = { },
        onCancel = { },
        primaryTextColor = Color.Black,
        secondaryColor = Color(0xFFB3E5FC),
        text = "Text text text"
    )
}
