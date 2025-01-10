package com.example.bookbank.views.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.ui.theme.yellowColor
import com.example.bookbank.util.Dimens.MediumPadding1

@Composable
fun RequestReturnBookListItem(modifier: Modifier = Modifier, onClick: (Boolean) -> Unit) {

    Column {

        Spacer(Modifier.height(MediumPadding1))


        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("134", style = TextStyle(
                fontSize = 17.sp,
                fontFamily = interBold,
                textDecoration = TextDecoration.Underline
            ), textAlign = TextAlign.Center, modifier = Modifier
                .weight(1f)
                .clickable {
                    onClick(true)
                })


            Text(
                "12-Dec-2024", style = TextStyle(
                    fontSize = 17.sp, fontFamily = interRegular
                ), textAlign = TextAlign.Center, modifier = Modifier.weight(1f)
            )


            Button(
                onClick = {

                }, shape = RoundedCornerShape(7.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = yellowColor,

                    ), modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(0.07f)
            ) {
                Text(
                    "Borrowed", style = TextStyle(
                        fontSize = 13.sp, fontFamily = interRegular, color = buttonColor
                    )
                )
            }

        }
        Spacer(Modifier.height(MediumPadding1))

        HorizontalDivider(thickness = 1.dp, color = buttonColor)
    }

}