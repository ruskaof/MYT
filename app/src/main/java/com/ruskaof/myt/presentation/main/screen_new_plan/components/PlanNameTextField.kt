package com.ruskaof.myt.presentation.main.screen_new_plan.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PlanNameTextField(
    caption: String,
    maxLength: Int,
    textState: String,
    onValueChange: (String) -> Unit,
    labelsColor: Color,
    backgroundColor: Color,
    clearText: () -> Unit
) {
    Column {
        Text(
            text = caption,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = labelsColor
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = textState,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = backgroundColor,
                cursorColor = Color.Black,
                disabledLabelColor = backgroundColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                onValueChange(it)
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            trailingIcon = {
                if (textState.isNotEmpty()) {
                    IconButton(onClick = { clearText() }) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null
                        )
                    }
                }
            }
        )
        Text(
            text = "${textState.length} / $maxLength",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            textAlign = TextAlign.End,
            color = labelsColor
        )
    }
}

@Preview
@Composable
fun PlanNameTextFieldPreview() {
    PlanNameTextField(
        caption = "Caption",
        maxLength = 30,
        textState = "text",
        onValueChange = {},
        labelsColor = Color(0xFFB3E5FC),
        backgroundColor = Color(0xFFB3E5FC)
    ) {

    }
}