package com.example.bookbank.views.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bookbank.R
import com.example.bookbank.ui.theme.blueColor
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.Dimens.MediumPadding2
import com.example.bookbank.util.Dimens.SmallPadding
import com.example.bookbank.util.Dimens.XSmallPadding
import com.example.bookbank.util.navgraph.Route
import com.example.bookbank.views.common.CustomButton
import com.example.bookbank.views.common.CustomTextField
import com.example.bookbank.views.common.GoogleButton

@Composable
fun AuthScreen(navController:NavController,modifier: Modifier = Modifier) {


    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var isPasswordShowing by remember { mutableStateOf(false) }

    var isLogin by remember { mutableStateOf(false) }

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
            Image(
                painter = painterResource(R.drawable.mainlogo),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.size(250.dp)
            )

            Spacer(Modifier.height(SmallPadding))

            Text(
                "Email", style = TextStyle(
                    fontSize = 20.sp, fontFamily = interRegular
                ), modifier = Modifier.align(Alignment.Start)
            )

            Spacer(Modifier.height(SmallPadding))

            CustomTextField(modifier = Modifier,
                text = "Enter your Email",
                bgColor = Color.White,
                borderColor = buttonColor,
                textColor = buttonColor,
                textValue = emailText,
                borderRad = 5,
                isPasswordField = false,
                error = emailError,
                keyboardType = KeyboardType.Email,
                leadingIcon = {
                    Image(
                        painter = painterResource(R.drawable.mail_24px),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(buttonColor)
                    )
                },
                onValueChange = {
                    emailText = it
                })

            Spacer(Modifier.height(MediumPadding2))

            Text(
                "Password", style = TextStyle(
                    fontSize = 20.sp, fontFamily = interRegular
                ), modifier = Modifier.align(Alignment.Start)
            )

            Spacer(Modifier.height(SmallPadding))

            CustomTextField(modifier = Modifier,
                text = "Enter your password",
                bgColor = Color.White,
                borderColor = buttonColor,
                textColor = buttonColor,
                textValue = passwordText,
                borderRad = 5,
                keyboardType = KeyboardType.Password,
                isPasswordField = !isPasswordShowing,
                error = passwordError,
                leadingIcon = {
                    Image(
                        painter = painterResource(R.drawable.baseline_lock_24),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(buttonColor)
                    )
                },
                trailingIcon = {
                    if (isPasswordShowing) {
                        IconButton(onClick = { isPasswordShowing = false }) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = "hide_password"
                            )
                        }
                    } else {
                        IconButton(onClick = { isPasswordShowing = true }) {
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = "hide_password"
                            )
                        }
                    }
                },
                onValueChange = {
                    passwordText = it
                })

            Spacer(Modifier.height(SmallPadding))

            Text(
                "Forgot Password?", style = TextStyle(
                    fontSize = 17.sp, fontFamily = interRegular, color = blueColor
                ), modifier = Modifier.align(Alignment.End)
            )

            Spacer(Modifier.height(MediumPadding2))

            CustomButton(
                text = if (isLogin) "Sign In" else "Sign Up",
                color = buttonColor,
                textSize = 17,
                textColor = Color.White,
                isLoading = false,
                radius = 8,
                height = 70,
                modifier = Modifier
            ) {
                if(!isLogin){
                    navController.navigate(Route.SetProfileScreen.route)
                } else {
                    navController.navigate(Route.MainScreen.route)
                }
            }

            Spacer(Modifier.height(MediumPadding2))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Already have an account?", style = TextStyle(
                        fontSize = 17.sp, fontFamily = interRegular, color = buttonColor
                    )
                )

                Spacer(Modifier.width(XSmallPadding))

                Text("Sign In", style = TextStyle(
                    fontSize = 17.sp, fontFamily = interRegular, color = blueColor
                ), modifier = Modifier.clickable {
                    isLogin = !isLogin
                })
            }

            Spacer(Modifier.height(MediumPadding1))

            Text(
                "Or with", style = TextStyle(
                    fontSize = 17.sp, fontFamily = interRegular, color = buttonColor
                )
            )

            Spacer(Modifier.height(MediumPadding1))

            GoogleButton(
                text = "Google",
                color = Color.White,
                textSize = 17,
                textColor = buttonColor,
                isLoading = false,
                radius = 7,
                height = 70,
                modifier = Modifier
            ) { }

        }
    }
}