package com.ruskaof.myt.presentation.main.screen_menu.screen_archieve.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ruskaof.myt.common.Constants
import com.ruskaof.myt.presentation.main.common.PlanNameTextField
import com.ruskaof.myt.presentation.theme.AppTheme

@Composable
private fun RedactingPlanDialogUI(
    dialogIsOpen: MutableState<Boolean>,
    onOk: () -> Unit,
    onCancel: () -> Unit,
    primaryTextColor: Color = AppTheme.colors.primaryTextColor,
    secondaryColor: Color = AppTheme.colors.secondary,
    backgroundColor: Color = AppTheme.colors.primaryBackground,
    text: String,
    textFieldState: MutableState<String>
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
        elevation = 8.dp,
    ) {
        Column(
            Modifier.background(Color.White)
        ) {
            PlanNameTextField(
                caption = "New name",
                maxLength = Constants.MAX_PLAN_NAME_LENGTH,
                textState = textFieldState.value,
                onValueChange = {
                    if (it.length <= Constants.MAX_PLAN_NAME_LENGTH) textFieldState.value = it
                },
                labelsColor = secondaryColor,
                backgroundColor = secondaryColor,
                clearText = { textFieldState.value = "" },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
            )
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

@SuppressLint("UnrememberedMutableState")
@Composable
fun RedactionPlanDialog(
    openDialogCustom: MutableState<Boolean>,
    onOk: () -> Unit,
    onCancel: () -> Unit,
    text: String,
    textFieldState: MutableState<String>
) {
    Dialog(onDismissRequest = {
        openDialogCustom.value = false
        textFieldState.value = ""
    }) {
        RedactingPlanDialogUI(
            dialogIsOpen = openDialogCustom,
            onOk = onOk,
            onCancel = onCancel,
            text = text,
            textFieldState = textFieldState
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
private fun RedactingPlanDialogPreview() {
    RedactingPlanDialogUI(
        dialogIsOpen = mutableStateOf(true),
        onOk = { },
        onCancel = { },
        primaryTextColor = Color.Black,
        secondaryColor = Color(0xFFB3E5FC),
        text = "Enter a new plan name",
        backgroundColor = Color.White,
        textFieldState = mutableStateOf("sofdsafa")
    )
}
