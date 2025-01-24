package com.example.bookbank.views.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.bookbank.R
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.Dimens.SmallPadding
import com.example.bookbank.util.Dimens.XSmallPadding
import com.example.bookbank.util.NetworkResult
import com.example.bookbank.util.navgraph.Route
import com.example.bookbank.viewmodels.BookViewModel
import com.example.bookbank.viewmodels.MainViewModel
import com.example.bookbank.viewmodels.UtilViewModel
import com.example.bookbank.views.common.CategoriesTab
import com.example.bookbank.views.common.CustomTextField
import com.example.bookbank.views.dialog.CartDetailDialog
import com.example.bookbank.views.home.common.BookListItem
import com.google.gson.Gson
import java.util.Locale

@Composable
fun HomeScreen(
    navController: NavController,
    bookViewModel: BookViewModel,
    modifier: Modifier = Modifier
) {

    var searchText by remember { mutableStateOf("") }
    val tabs = listOf("All", "School", "Intermediate", "Business")
    val bookResponse by bookViewModel.bookResponse.collectAsState()
    var category by remember { mutableIntStateOf(0) }
    val books by bookViewModel.books.collectAsState()
    val gson = Gson()

    LaunchedEffect(category) {
        bookViewModel.getAllBooks(tabs[category].lowercase(Locale.getDefault()))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(SmallPadding)
    ) {

        Column {

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

            CategoriesTab(tabs) {
                category = it
            }

            Spacer(Modifier.height(MediumPadding1))

            when (bookResponse) {
                is NetworkResult.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally), color = buttonColor
                    )
                    Log.d("Err", "HomeScreen: Loading")
                }

                is NetworkResult.Error -> {
                    Log.d("Err", "HomeScreen: ${bookResponse.error}")
                }

                is NetworkResult.Success -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2), // 2 columns
                        horizontalArrangement = Arrangement.spacedBy(16.dp), // Space between columns
                        verticalArrangement = Arrangement.spacedBy(16.dp) // Space between rows
                    ) {
                        items(books.size) { book ->
                            BookListItem(book = books[book]) {
                                val bookJson =
                                    gson.toJson(books[book]) // Serialize the book object to JSON
                                navController.navigate(Route.BookDetailScreen.setBookData(bookJson))
                            }
                        }
                    }
                    Log.d("Err", "HomeScreen: Fine")
                }

                else -> {
                    Log.d("Err", "HomeScreen: Else")
                }
            }


        }







    }
}