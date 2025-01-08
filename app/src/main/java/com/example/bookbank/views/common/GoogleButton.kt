package com.example.bookbank.views.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbank.R
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.util.Dimens.XXSmallPadding

@Composable
fun GoogleButton(
    text: String,
    color: Color,
    textSize: Int = 15,
    textColor: Color,
    isLoading: Boolean,
    radius: Int = 4,
    height: Int,
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
            modifier = Modifier
                .fillMaxSize()
                .border(1.dp, buttonColor, RoundedCornerShape(7.dp))
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = textColor, strokeWidth = 2.dp, modifier = Modifier.size(24.dp)
                )
            } else {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Image(
                        painter = painterResource(R.drawable.google),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )

                    Spacer(Modifier.width(XXSmallPadding))

                    Text(
                        text = text,
                        color = textColor,
                        fontSize = textSize.sp,
                        fontFamily = interRegular,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}