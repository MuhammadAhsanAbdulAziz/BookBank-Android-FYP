package com.example.bookbank.views.dialog

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.bookbank.R
import com.example.bookbank.models.BooksRequest
import com.example.bookbank.models.BooksRequired
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.Dimens.XSmallPadding
import com.example.bookbank.util.Dimens.XXSmallPadding
import com.example.bookbank.util.Helper
import com.example.bookbank.util.NetworkResult
import com.example.bookbank.viewmodels.BookViewModel
import com.example.bookbank.viewmodels.MainViewModel
import com.example.bookbank.viewmodels.UtilViewModel
import com.example.bookbank.views.common.CustomButton
import com.example.bookbank.views.request.common.BookOrderDetailListItem

@Composable
fun CartDetailDialog(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    utilViewModel: UtilViewModel,
    bookViewModel: BookViewModel
) {
    val booksToOrder = bookViewModel.booksForOrder.collectAsState()
    val bookFormResponse = bookViewModel.bookFormResponse.collectAsState()
    val selectedOption = remember { mutableIntStateOf(0) }
    val isChecked = remember { mutableStateOf(false) }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim))
    val userData by mainViewModel.readUserData().collectAsState(initial = null)
    // State to control the animation progress
    val progress by animateLottieCompositionAsState(
        composition = composition, iterations = LottieConstants.IterateForever // Infinite loop
    )
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }


    LaunchedEffect(bookFormResponse.value) {
        when (bookFormResponse.value) {
            is NetworkResult.Loading -> {
                isLoading = true
            }

            is NetworkResult.Success -> {
                isLoading = false
                Toast.makeText(context, "Order Successfully placed", Toast.LENGTH_SHORT).show()

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
            .fillMaxWidth()
            .background(
                Color.White, shape = RoundedCornerShape(30.dp)
            ) // White background with rounded corners
            .border(
                width = 1.dp, // Adjust the border thickness as needed
                color = MaterialTheme.colorScheme.secondary, // Set border color
                shape = RoundedCornerShape(30.dp) // Rounded corners for the border
            )
            .clip(RoundedCornerShape(30.dp))
    ) {

        Column(
            Modifier.padding(MediumPadding1)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Spacer(Modifier.weight(1f))

                Text(
                    "Selected Books", style = TextStyle(
                        fontSize = 16.sp, color = buttonColor, fontFamily = interBold
                    )
                )

                Spacer(Modifier.weight(1f))

                IconButton(onClick = {
                    utilViewModel.triggerCartDialog(false)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                    )

                }

            }

            Spacer(Modifier.height(MediumPadding1))

            if (booksToOrder.value.isNotEmpty()) {
                LazyColumn {
                    items(booksToOrder.value.size) {
                        BookOrderDetailListItem(
                            bookData = booksToOrder.value[it],
                            isCartItem = true,
                            onRemove = {
                                bookViewModel.removeBooksForOrder(booksToOrder.value[it])
                                utilViewModel.decreaseCart()
                            })
                    }
                }
            } else {
                LottieAnimation(
                    composition = composition,
                    progress = { progress },
                )
            }


            Row(verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(colors = RadioButtonColors(
                        selectedColor = buttonColor,
                        unselectedColor = Color.Black,
                        disabledSelectedColor = Color.White,
                        disabledUnselectedColor = Color.White
                    ), selected = selectedOption.intValue == 0, onClick = {
                        selectedOption.intValue = 0
                    })

                    Text(
                        "6 months", style = TextStyle(
                            fontSize = 16.sp, color = buttonColor, fontFamily = interRegular
                        )
                    )
                }

                Spacer(Modifier.width(XSmallPadding))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(colors = RadioButtonColors(
                        selectedColor = buttonColor,
                        unselectedColor = Color.Black,
                        disabledSelectedColor = Color.White,
                        disabledUnselectedColor = Color.White
                    ), selected = selectedOption.intValue == 1, onClick = {
                        selectedOption.intValue = 1
                    })

                    Text(
                        "12 months", style = TextStyle(
                            fontSize = 16.sp, color = buttonColor, fontFamily = interRegular
                        )
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isChecked.value, onCheckedChange = {
                        isChecked.value = it
                    }, colors = CheckboxDefaults.colors(
                        checkmarkColor = Color.White,
                        checkedColor = buttonColor,
                        uncheckedColor = buttonColor,
                    )
                )

                Text(
                    " I pledge to use these books carefully and return all books to ICC Book Bank after examination.",
                    style = TextStyle(
                        fontSize = 14.sp, color = buttonColor, fontFamily = interBold
                    )
                )

            }

            Spacer(Modifier.height(MediumPadding1))

            Row(verticalAlignment = Alignment.CenterVertically) {
                CustomButton(
                    text = "Cancel",
                    color = buttonColor.copy(alpha = 0.3F),
                    textSize = 14,
                    textColor = Color.White,
                    isLoading = false,
                    radius = 7,
                    height = 50,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = XXSmallPadding)
                ) {
                    utilViewModel.triggerCartDialog(false)
                }

                CustomButton(
                    text = "Submit Request",
                    color = buttonColor,
                    textSize = 14,
                    textColor = Color.White,
                    isLoading = isLoading,
                    radius = 7,
                    height = 50,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = XXSmallPadding)
                ) {
                    if (booksToOrder.value.isNotEmpty()) {
                        if (isChecked.value) {
                            isLoading = true
                            val data = if (selectedOption.intValue == 0) {
                                Helper.getDateWithMonthsAdded(6)
                            } else {
                                Helper.getDateWithMonthsAdded(12)
                            }

                            val books = booksToOrder.value.map { books ->
                                BooksRequired(
                                    book_title = books.title
                                )
                            }

                            bookViewModel.insertBookForm(
                                BooksRequest(
                                    address = userData!!.address,
                                    book_return_date = data,
                                    books_required = books,
                                    father_name = userData!!.father_name,
                                    mobile = userData!!.mobile,
                                    name = userData!!.name,
                                    student_cnic = userData!!.cnic
                                )
                            )
                        } else {
                            Toast.makeText(
                                context, "Please accept the pledge first", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }


        }
    }
}