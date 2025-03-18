package com.example.bookbank.views.home.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.bookbank.models.BookData
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.Dimens.XXSmallPadding
import com.example.bookbank.viewmodels.BookViewModel
import com.example.bookbank.viewmodels.UtilViewModel
import com.example.bookbank.views.common.CustomButton
import com.example.bookbank.views.common.SearchBooksScreen

@Composable
fun BookDetailScreen(
    navController: NavController,
    utilViewModel: UtilViewModel,
    modifier: Modifier = Modifier,
    bookViewModel: BookViewModel,
    book: BookData
) {

    val context = LocalContext.current


    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier
                .fillMaxSize()
                .padding(MediumPadding1),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SearchBooksScreen(viewModel = bookViewModel, navController = navController)

            Spacer(Modifier.height(MediumPadding1))

            Row {

                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(imageVector = Icons.Filled.ArrowBackIosNew, tint = buttonColor, contentDescription = null)
                }

                Spacer(Modifier.weight(1f))

                AsyncImage(
                    model = book.image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(height = 200.dp, width = 170.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(Modifier.weight(2f))
            }

            Spacer(Modifier.height(MediumPadding1))

            Card(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(
                    Color.White
                ),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                LazyColumn(modifier = Modifier.padding(MediumPadding1)) {
                    item{

                        Text(
                            book.title, style = TextStyle(
                                fontSize = 18.sp, color = buttonColor, fontFamily = interBold
                            ), modifier = Modifier.align(Alignment.Start)
                        )

                        Text(
                            book.author, style = TextStyle(
                                fontSize = 15.sp, color = Color.Gray, fontFamily = interRegular
                            ), modifier = Modifier.align(Alignment.Start)
                        )

                        Spacer(Modifier.height(MediumPadding1))

                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Text(
                                "Category:", style = TextStyle(
                                    fontSize = 18.sp, color = buttonColor, fontFamily = interBold
                                )
                            )

                            Spacer(Modifier.width(XXSmallPadding))

                            Text(
                                book.category, style = TextStyle(
                                    fontSize = 18.sp, color = buttonColor, fontFamily = interRegular
                                )
                            )

                        }

                        Spacer(Modifier.height(MediumPadding1))

                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Text(
                                "Available Copies:", style = TextStyle(
                                    fontSize = 18.sp, color = buttonColor, fontFamily = interBold
                                )
                            )

                            Spacer(Modifier.width(XXSmallPadding))

                            Text(
                                "${book.availableCopies} left", style = TextStyle(
                                    fontSize = 18.sp, color = buttonColor, fontFamily = interRegular
                                )
                            )

                        }

                        Spacer(Modifier.height(MediumPadding1))

                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Text(
                                "Price:", style = TextStyle(
                                    fontSize = 18.sp, color = buttonColor, fontFamily = interBold
                                )
                            )

                            Spacer(Modifier.width(XXSmallPadding))

                            Text(
                                "${book.price} rs", style = TextStyle(
                                    fontSize = 18.sp, color = buttonColor, fontFamily = interRegular
                                )
                            )

                        }

                        Spacer(Modifier.height(MediumPadding1))

                        Row {
                            Spacer(Modifier.weight(1f))

                            CustomButton(
                                text = "Add Book",
                                color = if (book.availableCopies == 0) buttonColor.copy(0.5f) else buttonColor,
                                textSize = 15,
                                textColor = Color.White,
                                isLoading = false,
                                radius = 6,
                                height = 40,
                                modifier = Modifier
                                    .fillMaxWidth(0.6f)
                            ) {
                                if (book.availableCopies != 0) {
                                    val res = bookViewModel.insertBooksForOrder(book, context)
                                    if (res) {
                                        utilViewModel.increaseCart()
                                    }
                                }
                            }

                            Spacer(Modifier.weight(1f))
                        }

                    }
                }
            }

        }

    }
}