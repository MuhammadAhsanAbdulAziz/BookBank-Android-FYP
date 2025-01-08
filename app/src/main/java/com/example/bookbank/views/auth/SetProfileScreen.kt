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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bookbank.R
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.Dimens.MediumPadding2
import com.example.bookbank.util.Dimens.SmallPadding
import com.example.bookbank.util.navgraph.Route
import com.example.bookbank.views.common.CustomButton
import com.example.bookbank.views.common.CustomTextField

@Composable
fun SetProfileScreen(navController: NavController, modifier: Modifier = Modifier) {


    var nameText by remember { mutableStateOf("") }
    var fNameText by remember { mutableStateOf("") }
    var addressText by remember { mutableStateOf("") }
    var phoneText by remember { mutableStateOf("") }
    var cnicText by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf<String?>(null) }
    var fNameError by remember { mutableStateOf<String?>(null) }
    var addressError by remember { mutableStateOf<String?>(null) }
    var phoneError by remember { mutableStateOf<String?>(null) }
    var cnicError by remember { mutableStateOf<String?>(null) }

    val phoneMaxLength = 10
    val cnicMaxLength = 13

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(MediumPadding1)

    ) {
        Column(
            verticalArrangement = Arrangement.Center,
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
                    "Set Profile", style = TextStyle(
                        fontSize = 25.sp, fontFamily = interBold
                    )
                )

                Spacer(Modifier.weight(1f))
            }

            Spacer(Modifier.height(SmallPadding))

            Text(
                "Name", style = TextStyle(
                    fontSize = 20.sp, fontFamily = interRegular
                ), modifier = Modifier.align(Alignment.Start)
            )

            Spacer(Modifier.height(SmallPadding))

            CustomTextField(modifier = Modifier,
                text = "Enter your Name",
                bgColor = Color.White,
                borderColor = buttonColor,
                textColor = buttonColor,
                textValue = nameText,
                borderRad = 5,
                isPasswordField = false,
                error = nameError,
                keyboardType = KeyboardType.Text,
                onValueChange = {
                    nameText = it
                })

            Spacer(Modifier.height(MediumPadding2))

            Text(
                "Father's Name", style = TextStyle(
                    fontSize = 20.sp, fontFamily = interRegular
                ), modifier = Modifier.align(Alignment.Start)
            )

            Spacer(Modifier.height(SmallPadding))

            CustomTextField(modifier = Modifier,
                text = "Enter your father's name",
                bgColor = Color.White,
                borderColor = buttonColor,
                textColor = buttonColor,
                textValue = fNameText,
                borderRad = 5,
                keyboardType = KeyboardType.Text,
                error = fNameError,
                onValueChange = {
                    fNameText = it
                })

            Spacer(Modifier.height(MediumPadding2))

            Text(
                "Address", style = TextStyle(
                    fontSize = 20.sp, fontFamily = interRegular
                ), modifier = Modifier.align(Alignment.Start)
            )

            Spacer(Modifier.height(SmallPadding))

            CustomTextField(modifier = Modifier,
                text = "Enter your address",
                bgColor = Color.White,
                borderColor = buttonColor,
                textColor = buttonColor,
                textValue = addressText,
                borderRad = 5,
                keyboardType = KeyboardType.Text,
                error = addressError,

                onValueChange = {
                    addressText = it
                })

            Spacer(Modifier.height(MediumPadding2))

            Text(
                "Phone No", style = TextStyle(
                    fontSize = 20.sp, fontFamily = interRegular
                ), modifier = Modifier.align(Alignment.Start)
            )

            Spacer(Modifier.height(SmallPadding))

            CustomTextField(modifier = Modifier,
                text = "",
                bgColor = Color.White,
                borderColor = buttonColor,
                textColor = buttonColor,
                prefixText = "+92",
                textValue = phoneText,
                borderRad = 5,
                keyboardType = KeyboardType.Phone,
                error = phoneError,

                onValueChange = {
                    if (it.length <= phoneMaxLength) phoneText = it
                })

            Spacer(Modifier.height(MediumPadding2))

            Text(
                "CNIC", style = TextStyle(
                    fontSize = 20.sp, fontFamily = interRegular
                ), modifier = Modifier.align(Alignment.Start)
            )

            Spacer(Modifier.height(SmallPadding))

            CustomTextField(modifier = Modifier,
                text = "Enter your CNIC",
                bgColor = Color.White,
                borderColor = buttonColor,
                textColor = buttonColor,
                textValue = cnicText,
                borderRad = 5,
                keyboardType = KeyboardType.Text,
                error = cnicError,

                onValueChange = {
                    if (it.length <= cnicMaxLength) cnicText = it
                })

            Spacer(Modifier.height(MediumPadding2))

            CustomButton(
                text = "Next",
                color = buttonColor,
                textSize = 17,
                textColor = Color.White,
                isLoading = false,
                radius = 8,
                height = 70,
                modifier = Modifier
            ) {
                navController.navigate(Route.OTPScreen.route)
            }
        }
    }
}