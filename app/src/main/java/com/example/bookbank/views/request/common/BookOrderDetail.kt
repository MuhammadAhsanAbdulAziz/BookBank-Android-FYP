package com.example.bookbank.views.request.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.ui.theme.lightYellowColor
import com.example.bookbank.ui.theme.yellowColor
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.Dimens.SmallPadding

@Composable
fun BookOrderDetail(modifier: Modifier = Modifier,onClose : () -> Unit) {

    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = lightYellowColor)
    ) {
        Column(modifier = Modifier
            .padding(MediumPadding1)
            .fillMaxSize()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    onClose()
                }) {
                    Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)
                }

                Spacer(Modifier.weight(1f))

                Text(
                    "Book ID ${"134"}", style = TextStyle(
                        fontSize = 18.sp, fontFamily = interBold
                    ), textAlign = TextAlign.Center
                )

                Spacer(Modifier.weight(1f))

            }

            Spacer(Modifier.height(SmallPadding))


            Text(
                "Books Requested", style = TextStyle(
                    fontSize = 17.sp, fontFamily = interBold
                ), textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(SmallPadding))

            LazyColumn(modifier = Modifier.fillMaxHeight(0.7f)) {
                items(4){
                    BookOrderDetailListItem()
                }
            }

            Spacer(Modifier.height(SmallPadding))

            Box(modifier = Modifier.border(1.dp, buttonColor,RoundedCornerShape(10.dp))){
                Row(modifier = Modifier.padding(SmallPadding)) {
                    Text(
                        "Return Date", style = TextStyle(
                            fontSize = 17.sp, fontFamily = interBold
                        ), textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.weight(1f))

                    Text(
                        "12-Dec-2024", style = TextStyle(
                            fontSize = 20.sp, fontFamily = interRegular
                        ), textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(Modifier.height(SmallPadding))

            Button(
                onClick = {

                }, shape = RoundedCornerShape(7.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = yellowColor

                    ),modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    "Borrowed", style = TextStyle(
                        fontSize = 13.sp, fontFamily = interRegular, color = buttonColor
                    )
                )
            }

        }
    }
}