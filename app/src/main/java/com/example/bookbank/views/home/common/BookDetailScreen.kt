package com.example.bookbank.views.home.common

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.example.bookbank.R
import com.example.bookbank.models.BookData
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.Dimens.SmallPadding
import com.example.bookbank.viewmodels.BookViewModel
import com.example.bookbank.viewmodels.UtilViewModel
import com.example.bookbank.views.common.CustomButton
import com.example.bookbank.views.common.CustomTextField
import com.google.gson.Gson

@Composable
fun BookDetailScreen(
    navController: NavController,
    utilViewModel: UtilViewModel,
    modifier: Modifier = Modifier,
    bookViewModel: BookViewModel,
    book: BookData
) {

    var searchText by remember { mutableStateOf("") }
    var context = LocalContext.current


    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier
                .fillMaxSize()
                .padding(MediumPadding1),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CustomTextField(
                modifier = Modifier.height(50.dp),
                text = "Search",
                bgColor = Color.White,
                borderColor = buttonColor,
                textColor = buttonColor,
                textValue = searchText,
                borderRad = 40,
                keyboardType = KeyboardType.Text,
                leadingIcon = {
                    Image(
                        painter = painterResource(R.drawable.search_24px),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(buttonColor)
                    )
                },
            ) {
                searchText = it
            }

            Spacer(Modifier.height(MediumPadding1))

            Row {

                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)
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
                Column(modifier = Modifier.padding(MediumPadding1)) {

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

                    Spacer(Modifier.height(SmallPadding))

                    Text(
                        "Description", style = TextStyle(
                            fontSize = 14.sp, color = buttonColor, fontFamily = interBold
                        ), modifier = Modifier.align(Alignment.Start)
                    )

                    Spacer(Modifier.height(SmallPadding))

                    LazyColumn(modifier = Modifier.fillMaxHeight(0.7f)) {
                        item {
                            Text(
                                "The English IX textbook for the Sindh Board  is designed to align with the educational curriculum set by the Board of Secondary Education, Sindh. This textbook is intended to enhance their English language skills in reading, writing, speaking, and comprehension English language skills in reading, writing, speaking, and comprehension",
                                style = TextStyle(
                                    fontSize = 14.sp, color = buttonColor, fontFamily = interRegular
                                ),
                                modifier = Modifier.align(Alignment.Start)
                            )
                        }
                    }

                    Spacer(Modifier.height(SmallPadding))

                    CustomButton(
                        text = "Add Book",
                        color = buttonColor,
                        textSize = 15,
                        textColor = Color.White,
                        isLoading = false,
                        radius = 6,
                        height = 40,
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        val res = bookViewModel.insertBooksForOrder(book,context)
                        if(res) {
                            utilViewModel.increaseCart()
                        }
                    }

                }
            }

        }

    }
}