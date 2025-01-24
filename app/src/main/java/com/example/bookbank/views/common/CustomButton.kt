package com.example.bookbank.views.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interRegular

@Composable
fun CustomButton(
    text: String,
    color: Color,
    textSize : Int = 15,
    textColor: Color,
    isLoading: Boolean,
    radius:Int = 4,
    height:Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(height.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(radius.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = buttonColor,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Text(
                    text = text,
                    color = textColor,
                    fontSize = textSize.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = interRegular,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}