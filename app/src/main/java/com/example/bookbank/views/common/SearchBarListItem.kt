package com.example.bookbank.views.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bookbank.models.BookData
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.util.Dimens.SmallPadding
import com.example.bookbank.util.Dimens.XXSmallPadding

@Composable
fun SearchBarListItem(bookData: BookData,modifier: Modifier = Modifier,onClick:()-> Unit) {
    Box(modifier = Modifier.padding(SmallPadding)
        .shadow(1.dp,)
        .clickable {
            onClick()
        }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(SmallPadding)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = bookData.image,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(5.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.width(SmallPadding))

            Column {
                Text(
                    text = bookData.title, // Assuming `BookData` has a `name` field
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontFamily = interBold
                    )
                )

                Spacer(Modifier.height(XXSmallPadding))

                Text(
                    text = bookData.author, // Assuming `BookData` has a `name` field
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 15.sp,
                        fontFamily = interBold
                    )
                )

                Spacer(Modifier.height(XXSmallPadding))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Price:", // Assuming `BookData` has a `name` field
                        style = TextStyle(
                            color = Color.Gray,
                            fontSize = 16.sp,
                            fontFamily = interBold
                        )
                    )

                    Spacer(Modifier.width(XXSmallPadding))

                    Text(
                        text = "${bookData.price} rs", // Assuming `BookData` has a `name` field
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontFamily = interRegular
                        )
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Available:", // Assuming `BookData` has a `name` field
                        style = TextStyle(
                            color = Color.Gray,
                            fontSize = 16.sp,
                            fontFamily = interBold
                        )
                    )

                    Spacer(Modifier.width(XXSmallPadding))

                    Text(
                        text = "${bookData.availableCopies}", // Assuming `BookData` has a `name` field
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontFamily = interRegular
                        )
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Category:", // Assuming `BookData` has a `name` field
                        style = TextStyle(
                            color = Color.Gray,
                            fontSize = 16.sp,
                            fontFamily = interBold
                        )
                    )

                    Spacer(Modifier.width(XXSmallPadding))

                    Text(
                        text = bookData.category, // Assuming `BookData` has a `name` field
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontFamily = interRegular
                        )
                    )
                }
            }
        }
    }
}