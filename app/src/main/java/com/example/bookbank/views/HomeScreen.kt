package com.example.bookbank.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbank.R
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.Dimens.SmallPadding
import com.example.bookbank.util.Dimens.XSmallPadding
import com.example.bookbank.util.Dimens.XXSmallPadding
import com.example.bookbank.views.common.BookListItem
import com.example.bookbank.views.common.CategoriesTab
import com.example.bookbank.views.common.CustomTextField

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    var searchText by remember { mutableStateOf("") }
    val tabs = listOf("All", "School", "Intermediate", "Business")

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
            
            CategoriesTab(tabs){

            }

            Spacer(Modifier.height(MediumPadding1))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Fixed number of columns
                horizontalArrangement = Arrangement.spacedBy(16.dp), // Space between columns
                verticalArrangement = Arrangement.spacedBy(16.dp) // Space between rows
            ) {
                items(5) { item ->
                    BookListItem()
                }
            }


        }
    }
}