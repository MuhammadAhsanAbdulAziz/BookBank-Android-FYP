package com.example.bookbank.views.donate.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbank.R
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.ui.theme.textColor
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.Dimens.MediumPadding3
import com.example.bookbank.util.Dimens.PageIndicatorWidth
import com.example.bookbank.views.common.CustomButton

@Composable
fun DonateBooks(modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(R.drawable.stackbooks),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(250.dp),
        )

        Text(
            "Donate Books to us", style = TextStyle(
                fontSize = 24.sp, fontFamily = interBold, color = textColor
            )
        )

        Spacer(Modifier.height(MediumPadding1))
        
        CustomButton(
            text = "Donation Form",
            color = buttonColor,
            textSize = 16,
            textColor = Color.White,
            isLoading = false,
            radius = 6,
            height = 50,
            modifier = Modifier.width(200.dp)
        ) { }

    }

}