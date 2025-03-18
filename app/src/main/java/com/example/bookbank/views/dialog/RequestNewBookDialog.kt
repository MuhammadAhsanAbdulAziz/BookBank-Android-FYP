package com.example.bookbank.views.dialog

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbank.models.RequestNewBookRequest
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.Dimens.XSmallPadding
import com.example.bookbank.util.NetworkResult
import com.example.bookbank.viewmodels.BookViewModel
import com.example.bookbank.views.common.CustomButton
import com.example.bookbank.views.common.CustomTextField

@Composable
fun RequestNewBookDialog(
    modifier: Modifier = Modifier,
    bookViewModel: BookViewModel,
    onClose: () -> Unit

) {

    val bookFormResponse = bookViewModel.bookFormResponse.collectAsState()
    var bookName by remember { mutableStateOf("") }
    var bookNameError by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(bookFormResponse.value) {
        when (bookFormResponse.value) {
            is NetworkResult.Loading -> {
                isLoading = true
            }

            is NetworkResult.Success -> {
                isLoading = false
                Toast.makeText(context, "Request Successfully placed", Toast.LENGTH_SHORT).show()
                onClose()
            }

            is NetworkResult.Error -> {
                isLoading = false
                Toast.makeText(context, bookFormResponse.value.error.toString(), Toast.LENGTH_SHORT)
                    .show()
            }

            else -> {
                // Handle Idle or other states
                isLoading = false
            }
        }
    }

    Box(
        modifier = Modifier
            .padding(MediumPadding1)
            .fillMaxWidth()
            .background(
                Color.White, shape = RoundedCornerShape(11.dp)
            ) // White background with rounded corners
            .border(
                width = 1.dp, // Adjust the border thickness as needed
                color = Color.White, // Set border color
                shape = RoundedCornerShape(11.dp) // Rounded corners for the border
            )
            .clip(RoundedCornerShape(11.dp))
    ) {

        Column(
            Modifier.padding(MediumPadding1), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row {

                Spacer(Modifier.weight(1f))

                Text(
                    "Request New Book", style = TextStyle(
                        fontSize = 24.sp, color = buttonColor, fontFamily = interBold
                    )
                )

                Spacer(Modifier.weight(1f))


                Image(imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(buttonColor),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            onClose()

                        })

            }




            Spacer(Modifier.height(MediumPadding1))

            Text(
                "Title", style = TextStyle(
                    fontSize = 20.sp, color = buttonColor, fontFamily = interBold
                ), modifier = Modifier.align(Alignment.Start)
            )

            Spacer(Modifier.height(XSmallPadding))

            CustomTextField(
                modifier = Modifier,
                text = "Enter book title...",
                bgColor = Color.White,
                borderColor = buttonColor,
                textColor = buttonColor,
                error = bookNameError,
                textValue = bookName,
                borderRad = 10,
                keyboardType = KeyboardType.Text,
            ) {
                bookName = it
            }

            Spacer(Modifier.height(MediumPadding1))

            CustomButton(
                text = "Submit",
                color = buttonColor,
                textSize = 17,
                radius = 15,
                textColor = Color.White,
                isLoading = isLoading,
                height = 60
            ) {
                if (bookName.isEmpty()) {
                    bookNameError = "Please enter book name"
                    return@CustomButton
                }
                isLoading = true
                bookViewModel.insertRequestNewBook(
                    RequestNewBookRequest(
                        book_title = bookName
                    )
                )
            }

        }
    }
}
