package com.example.bookbank.views.request.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.views.common.RequestReturnBookListItem

@Composable
fun ReturnBooks(modifier: Modifier = Modifier) {

    var returnDetail by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {

        if (!returnDetail) {

            Text(
                "History of all books to return ", style = TextStyle(
                    fontSize = 14.sp, fontFamily = interRegular, color = Color.Gray
                )
            )

            Spacer(Modifier.height(MediumPadding1))

            Column {

                Row(
                    horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Books ID", style = TextStyle(
                            fontSize = 17.sp, fontFamily = interBold
                        ), textAlign = TextAlign.Center, modifier = Modifier.weight(1f)
                    )


                    Text(
                        "Return Date", style = TextStyle(
                            fontSize = 17.sp, fontFamily = interBold
                        ), textAlign = TextAlign.Center, modifier = Modifier.weight(1f)
                    )


                    Text(
                        "Status", style = TextStyle(
                            fontSize = 17.sp, fontFamily = interBold
                        ), textAlign = TextAlign.Center, modifier = Modifier.weight(1f)
                    )
                }


//                LazyColumn {
//                    items(4) {
//                        RequestReturnBookListItem {
//                            returnDetail = it
//                        }
//                    }
//                }


            }
        }  else {
            BookOrderDetail{
                returnDetail = false
            }
        }

    }
}