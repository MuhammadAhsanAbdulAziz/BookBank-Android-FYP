package com.example.bookbank.views.home.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bookbank.R
import com.example.bookbank.models.BookData
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.util.Dimens.XXSmallPadding

@Composable
fun BookListItem(book: BookData, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable {
        onClick()
    }) {
        AsyncImage(
            model = book.image,
            contentDescription = null,
            modifier = Modifier
                .size(height = 200.dp, width = 170.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(XXSmallPadding))

        Text(
            book.title,color = buttonColor, style = TextStyle(
                fontSize = 17.sp, fontFamily = interBold
            ), modifier = Modifier.align(Alignment.Start)
        )

        Spacer(Modifier.height(XXSmallPadding))

        Text(
            book.author,color = buttonColor, style = TextStyle(
                fontSize = 13.sp, fontFamily = interRegular
            ), modifier = Modifier.align(Alignment.Start)
        )

    }
}