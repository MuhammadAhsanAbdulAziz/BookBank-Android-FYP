package com.example.bookbank.views.menus

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.bookbank.R
import com.example.bookbank.ui.theme.appColor
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.crimsonRegular
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.util.Dimens.SmallPadding
import com.example.bookbank.viewmodels.UtilViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    modifier: Modifier = Modifier, utilViewModel: UtilViewModel
) {
    val cartAmount by utilViewModel.cartAmount.collectAsState()

    TopAppBar(

        title = {
            Text(
                text = "Book Bank", style = TextStyle(
                    fontFamily = crimsonRegular, fontSize = 24.sp, color = buttonColor
                )
            )
        },
        actions = {
            Row {
                Image(
                    painter = painterResource(R.drawable.shopping_cart_24px),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        buttonColor
                    ),
                    modifier = Modifier
                        .padding(SmallPadding)
                        .size(35.dp)
                        .clickable {
                            utilViewModel.triggerCartDialog(true)
                        }

                )

                if(cartAmount > 0) {

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .offset(
                                y = (10).dp, x = (-25).dp
                            ) // Negative offset to move the row upwards
                            .zIndex(1f)
                            .size(25.dp) // Set the size of the circle
                            .background(Color.White, CircleShape)
                        // Set the background color and shape
                    ) {
                        Text(
                            text = cartAmount.toString(),
                            fontFamily = interRegular,
                            color = buttonColor
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = appColor),
    )

}