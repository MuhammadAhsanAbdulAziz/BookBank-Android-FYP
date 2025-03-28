package com.example.bookbank.views.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.Dimens.SmallPadding
import com.example.bookbank.util.NetworkResult
import com.example.bookbank.util.customOverscroll
import com.example.bookbank.util.navgraph.Route
import com.example.bookbank.viewmodels.BookViewModel
import com.example.bookbank.views.common.CategoriesTab
import com.example.bookbank.views.common.SearchBooksScreen
import com.example.bookbank.views.home.common.BookListItem
import com.google.gson.Gson
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun HomeScreen(
    navController: NavController,
    bookViewModel: BookViewModel,
    modifier: Modifier = Modifier
) {

    val tabs = listOf("All", "School", "Intermediate", "Business")
    val bookResponse by bookViewModel.bookResponse.collectAsState()
    var category by remember { mutableIntStateOf(0) }
    val books by bookViewModel.books.collectAsState()
    val gson = Gson()
    val listState = rememberLazyListState()
    var animatedOverscrollAmount by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(category) {
        bookViewModel.getAllBooks(tabs[category].lowercase(Locale.getDefault()))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(SmallPadding)
    ) {

        Column {

            SearchBooksScreen(viewModel = bookViewModel, navController = navController)

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
                        verticalArrangement = Arrangement.spacedBy(16.dp), // Space between rows
                        modifier = Modifier
                            .customOverscroll(
                                listState,
                                onNewOverscrollAmount = { animatedOverscrollAmount = it }
                            )
                            .offset { IntOffset(0, animatedOverscrollAmount.roundToInt()) },
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