package com.example.bookbank.views.menus

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbank.R
import com.example.bookbank.ui.theme.appColor
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.crimsonRegular
import com.example.bookbank.util.Dimens.SmallPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    modifier: Modifier = Modifier,
) {


    TopAppBar(

        title = {
            Text(
                text = "Book Bank", style = TextStyle(
                    fontFamily = crimsonRegular, fontSize = 24.sp, color = buttonColor
                )
            )
        },
        actions = {
            Image(
                painter = painterResource(R.drawable.shopping_cart_24px),
                contentDescription = null,
                colorFilter = ColorFilter.tint(
                    buttonColor
                ),
                modifier = Modifier
                    .padding(SmallPadding)
                    .size(35.dp)

            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = appColor),
    )

}