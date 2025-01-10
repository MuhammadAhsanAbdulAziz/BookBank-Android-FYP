package com.example.bookbank.views.request.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.bookbank.R
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.util.Dimens.SmallPadding
import com.example.bookbank.util.Dimens.XSmallPadding
import com.example.bookbank.util.Dimens.XXSmallPadding

@Composable
fun BookOrderDetailListItem(modifier: Modifier = Modifier) {
    Column {
        Row {

            Image(
                painter = painterResource(R.drawable.bookimg),
                contentDescription = null,
                modifier = Modifier
                    .size(height = 90.dp, width = 70.dp)
                    .clip(RoundedCornerShape(5.dp)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(XSmallPadding)) {
                Text(
                    "Computer Science IX", style = TextStyle(
                        fontSize = 17.sp, fontFamily = interBold
                    ), modifier = Modifier.align(Alignment.Start)
                )

                Spacer(Modifier.height(XXSmallPadding))

                Text(
                    "Bill gates", style = TextStyle(
                        fontSize = 15.sp, fontFamily = interRegular
                    ), modifier = Modifier.align(Alignment.Start)
                )
            }

        }
        Spacer(Modifier.height(SmallPadding))
    }
}