package com.example.bookbank.views.request.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbank.R
import com.example.bookbank.ui.theme.interRegular

@Composable
fun RequestBooks(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            "History of all book request send ", style = TextStyle(
                fontSize = 14.sp, fontFamily = interRegular, color = Color.Gray
            )
        )

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