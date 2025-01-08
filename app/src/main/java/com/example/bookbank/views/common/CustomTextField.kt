package com.example.bookbank.views.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: String,
    bgColor: Color,
    borderColor: Color,
    textColor: Color,
    textValue: String,
    borderRad: Int,
    keyboardType: KeyboardType,

    prefixText: String = "",
    isPasswordField: Boolean = false,
    error: String? = null,

    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {
    Column {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(bgColor, shape = RoundedCornerShape(borderRad.dp))
                .border(
                    width = 1.dp,
                    color = if (error.isNullOrEmpty()) borderColor else Color.Red,
                    shape = RoundedCornerShape(borderRad.dp)
                )
                .clip(RoundedCornerShape(borderRad.dp))
        ) {
            TextField(
                value = textValue,

                onValueChange = onValueChange,
                keyboardOptions = KeyboardOptions(
                    autoCorrectEnabled = false,
                    keyboardType = keyboardType,
                ),
                prefix = {
                    Text(prefixText, style = TextStyle(color = textColor, fontSize = 15.sp))
                },
                visualTransformation = if (!isPasswordField) VisualTransformation.None else PasswordVisualTransformation(),
                placeholder = {
                    Box(
                        modifier = Modifier.fillMaxWidth(), // Ensures placeholder spans the full width
                    ) {
                        Text(
                            text = text,
                            textAlign = TextAlign.End,
                            style = TextStyle(color = textColor, fontSize = 15.sp)
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent) // Keep the TextField transparent
                    .clip(RoundedCornerShape(borderRad.dp)), // Clip the TextField to match rounded corners
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                colors = TextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.secondary,
                    unfocusedContainerColor = Color.Transparent, // Transparent background for TextField
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Red
                ),
                singleLine = true,
                textStyle = TextStyle(fontSize = 15.sp, color = textColor)
            )

        }

        if (!error.isNullOrEmpty()) {
            Text(
                text = error,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }
    }


}
