package com.example.bookbank.views.request.common

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbank.R
import com.example.bookbank.models.RequestBooksResponse
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.NetworkResult
import com.example.bookbank.viewmodels.BookViewModel
import com.example.bookbank.views.common.RequestReturnBookListItem

@Composable
fun RequestBooks(modifier: Modifier = Modifier, bookViewModel: BookViewModel) {

    val requestBooks = bookViewModel.requestBooksResponse.collectAsState()

    LaunchedEffect(Unit) {
        bookViewModel.getRequestBooks()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            "History of all book request send ", style = TextStyle(
                fontSize = 14.sp, fontFamily = interRegular, color = Color.Gray
            )
        )

        Spacer(Modifier.height(MediumPadding1))

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

        when (requestBooks.value) {
            is NetworkResult.Loading -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(Modifier.weight(1f))

                    CircularProgressIndicator(

                    )

                    Spacer(Modifier.weight(1f))
                }
                Log.d("Err", "HomeScreen: Loading")
            }

            is NetworkResult.Error -> {
                Log.d("Err", "HomeScreen: ${requestBooks.value.error}")
            }

            is NetworkResult.Success -> {
                val data = requestBooks.value.data as RequestBooksResponse

                if (data.data.isNotEmpty()) {
                    LazyColumn {
                        items(data.data.size) { index ->
                            RequestReturnBookListItem(requestBooksData = data.data[index]) {

                            }
                        }
                    }
                } else {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.computer),
                            contentDescription = null,
                            modifier = Modifier.size(270.dp)
                        )

                        Text(
                            "No request send yet", style = TextStyle(
                                fontSize = 17.sp, fontFamily = interRegular, color = Color.Gray
                            )
                        )
                    }
                }

            }

            else -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(R.drawable.computer),
                        contentDescription = null,
                        modifier = Modifier.size(270.dp)
                    )

                    Text(
                        "No request send yet", style = TextStyle(
                            fontSize = 17.sp, fontFamily = interRegular, color = Color.Gray
                        )
                    )
                }
            }
        }

    }
}