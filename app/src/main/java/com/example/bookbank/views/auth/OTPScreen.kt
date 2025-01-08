package com.example.bookbank.views.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bookbank.R
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.Dimens.MediumPadding2
import com.example.bookbank.util.navgraph.Route
import com.example.bookbank.views.common.CustomButton
import com.example.bookbank.views.common.OTPInputTextFields

@Composable
fun OTPScreen(navController: NavController, modifier: Modifier = Modifier) {


    val otpValues = remember { mutableStateListOf<String>("", "", "", "") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(MediumPadding1)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Image(
                        painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                        contentDescription = null
                    )
                }

                Spacer(Modifier.weight(1f))

                Text(
                    "OTP Verification", style = TextStyle(
                        fontSize = 25.sp, fontFamily = interBold
                    )
                )

                Spacer(Modifier.weight(1f))
            }

            Spacer(Modifier.height(MediumPadding1))

            Text(
                "Enter the verification code received at", style = TextStyle(
                    fontSize = 15.sp, fontFamily = interRegular
                )
            )

            Text(
                "+92222498342", style = TextStyle(
                    fontSize = 16.sp, fontFamily = interBold,
                )
            )

            Spacer(Modifier.height(MediumPadding2))

            OTPInputTextFields(otpValues = otpValues,
                otpLength = 4,
                onOtpInputComplete = { /* TODO: Make api calls or anything after validation */ },
                onUpdateOtpValuesByIndex = { index, value ->
                    otpValues[index] = value
                })

            Spacer(Modifier.height(MediumPadding2))

            CustomButton(
                text = "Confirm",
                color = buttonColor,
                textSize = 17,
                textColor = Color.White,
                isLoading = false,
                radius = 8,
                height = 70,
                modifier = Modifier
            ) {
                navController.navigate(Route.MainScreen.route)
            }
        }
    }
}