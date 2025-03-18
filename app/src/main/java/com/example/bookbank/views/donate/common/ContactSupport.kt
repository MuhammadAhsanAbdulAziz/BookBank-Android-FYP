package com.example.bookbank.views.donate.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.bookbank.R
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.ui.theme.textColor
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.Helper
import com.example.bookbank.views.common.CustomButton

@Composable
fun ContactSupport(modifier: Modifier = Modifier,name: String,) {

    val context = LocalContext.current

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.contactanim))
    val progress by animateLottieCompositionAsState(
        composition = composition, iterations = LottieConstants.IterateForever // Infinite loop
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

/*        Box(
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

        }*/

        LottieAnimation(
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(300.dp),
            composition = composition,
            progress = { progress },
        )

        Text(
            "Want to send a donation or have a query ? Reach out to us", style = TextStyle(
                fontSize = 18.sp, fontFamily = interBold, color = textColor, textAlign = TextAlign.Center
            )
        )

        Spacer(Modifier.height(MediumPadding1))

        CustomButton(
            text = "Contact Us",
            color = buttonColor,
            textSize = 16,
            textColor = Color.White,
            isLoading = false,
            radius = 6,
            height = 50,
            modifier = Modifier.width(200.dp)
        ) {
            Helper.openMailApp(context,name)
        }


    }

}