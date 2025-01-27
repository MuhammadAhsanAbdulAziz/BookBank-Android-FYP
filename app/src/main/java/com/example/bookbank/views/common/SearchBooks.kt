package com.example.bookbank.views.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookbank.R
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.util.navgraph.Route
import com.example.bookbank.viewmodels.BookViewModel
import com.google.gson.Gson


@Composable
fun SearchBooksScreen(navController: NavController, viewModel: BookViewModel) {
    var searchText by remember { mutableStateOf("") } // State for search input
    val searchedBooks by viewModel.searchedBooks.collectAsState() // Observes searched books
    val gson = Gson()

    Column {
        // Search TextField
        CustomTextField(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
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
            searchText = it // Update search text
            viewModel.searchBooks(searchText) // Trigger the search in ViewModel
        }

        // Display list of books if search results are available
        if (searchText.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
            ) {
                LazyColumn {
                    items(searchedBooks) { book ->
                        SearchBarListItem(book){
                            val bookJson =
                                gson.toJson(book) // Serialize the book object to JSON
                            navController.navigate(Route.BookDetailScreen.setBookData(bookJson))
                        }
                    }
                }
            }
        }
    }


}
