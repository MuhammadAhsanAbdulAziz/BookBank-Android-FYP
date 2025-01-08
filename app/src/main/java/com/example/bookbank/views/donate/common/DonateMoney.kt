package com.example.bookbank.views.donate.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.bookbank.R
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.ui.theme.textColor
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.Dimens.XSmallPadding

@Composable
fun DonateMoney(modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            "Send Donations To our Account", style = TextStyle(
                fontSize = 22.sp, fontFamily = interBold, color = textColor
            )
        )

        Spacer(Modifier.height(MediumPadding1))


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(buttonColor, RoundedCornerShape(10.dp))
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(XSmallPadding)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Image(
                        painter = painterResource(R.drawable.cardlogocircle2),
                        contentDescription = null,
                        modifier
                            .size(50.dp)
                            .offset(
                                y = (0).dp, x = (15).dp
                            ) // Negative offset to move the row upwards
                            .zIndex(1f)
                    )
                    Image(
                        painter = painterResource(R.drawable.cardlogocircle1),
                        contentDescription = null,
                        modifier
                            .size(50.dp)

                    )

                    Spacer(Modifier.weight(1f))

                    Text(
                        "MUHAMMAD UMER", style = TextStyle(
                            fontSize = 24.sp, fontFamily = interBold, color = Color.White
                        )
                    )

                    Spacer(Modifier.weight(1f))

                }

                Spacer(Modifier.height(MediumPadding1))

                Text(
                    "PK67MEZN0001580107001230", style = TextStyle(
                        fontSize = 20.sp, fontFamily = interRegular, color = Color.White
                    )
                )

                Spacer(Modifier.height(MediumPadding1))

                Row(modifier = Modifier.padding(horizontal = MediumPadding1)) {
                    Text(
                        "01580107006730", style = TextStyle(
                            fontSize = 20.sp, fontFamily = interRegular, color = Color.White
                        )
                    )

                    Spacer(Modifier.weight(1f))

                    Image(
                        painter = painterResource(R.drawable.content_copy_24px),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)

                    )

                }

                Spacer(Modifier.height(MediumPadding1))

            }

        }


    }

}