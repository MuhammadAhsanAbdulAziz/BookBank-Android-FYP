package com.example.bookbank.views.request.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbank.models.ReturnAndReturnBooksData
import com.example.bookbank.ui.theme.appColor
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.greenColor
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.ui.theme.redColor
import com.example.bookbank.ui.theme.yellowColor
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.Dimens.SmallPadding
import java.util.Locale

@Composable
fun BookOrderDetail(
    modifier: Modifier = Modifier,
    formData: ReturnAndReturnBooksData,
    isRequest: Boolean,
    onClose: () -> Unit
) {

    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(MediumPadding1)
                .fillMaxSize()
        ) {
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {
                        onClose()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            tint = buttonColor,
                            contentDescription = null
                        )
                    }

                    Spacer(Modifier.weight(1f))

                    Text(
                        "Book ID ${formData.form_number}", color = buttonColor, style = TextStyle(
                            fontSize = 18.sp, fontFamily = interBold
                        ), textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.weight(1f))

                }

                Spacer(Modifier.height(SmallPadding))


                Text(
                    "Books Requested", color = buttonColor, style = TextStyle(
                        fontSize = 17.sp, fontFamily = interBold
                    ), textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(SmallPadding))

            }

            items(formData.books_details.size) { index ->
                BookOrderDetailListItem(bookData = formData.books_details[index])

            }

            item {
                Spacer(Modifier.weight(1f))
            }

            item {

                Spacer(Modifier.height(SmallPadding))

                Box(modifier = Modifier.border(1.dp, buttonColor, RoundedCornerShape(10.dp))) {
                    Row(modifier = Modifier.padding(SmallPadding)) {
                        Text(
                            "Return Date", color = buttonColor, style = TextStyle(
                                fontSize = 17.sp, fontFamily = interBold
                            ), textAlign = TextAlign.Center
                        )

                        Spacer(Modifier.weight(1f))

                        Text(
                            formData.book_return_date, color = buttonColor, style = TextStyle(
                                fontSize = 20.sp, fontFamily = interRegular
                            ), textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(Modifier.height(SmallPadding))

                Row {
                    Spacer(Modifier.weight(1f))

                    Button(
                        onClick = {

                        }, shape = RoundedCornerShape(7.dp), colors = ButtonDefaults.buttonColors(
                            containerColor = when (if ((isRequest)) formData.request_status.lowercase(
                                Locale.ROOT
                            ) else formData.borrowed_status.lowercase(Locale.ROOT)) {
                                "pending" -> yellowColor
                                "rejected" -> redColor
                                "accepted" -> greenColor
                                "approved" -> appColor
                                "borrowed" -> yellowColor
                                "returned" -> greenColor
                                "not returned" -> redColor
                                else -> redColor
                            },

                            )
                    ) {
                        Text(
                            if (isRequest) formData.request_status else formData.borrowed_status,
                            color = buttonColor, style = TextStyle(
                                fontSize = 13.sp, fontFamily = interRegular, color = buttonColor
                            )
                        )
                    }

                    Spacer(Modifier.weight(1f))

                }

            }
        }
    }
}