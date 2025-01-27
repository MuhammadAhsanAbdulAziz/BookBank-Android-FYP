package com.example.bookbank.views.auth

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bookbank.R
import com.example.bookbank.models.CheckEmailRequest
import com.example.bookbank.models.LoginRequest
import com.example.bookbank.models.LoginResponse
import com.example.bookbank.ui.theme.blueColor
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.Dimens.MediumPadding2
import com.example.bookbank.util.Dimens.SmallPadding
import com.example.bookbank.util.Dimens.XSmallPadding
import com.example.bookbank.util.Helper.isValidEmail
import com.example.bookbank.util.Helper.isValidPassword
import com.example.bookbank.util.NetworkResult
import com.example.bookbank.util.customOverscroll
import com.example.bookbank.util.navgraph.Route
import com.example.bookbank.viewmodels.AuthViewModel
import com.example.bookbank.viewmodels.MainViewModel
import com.example.bookbank.views.common.CustomButton
import com.example.bookbank.views.common.CustomTextField
import kotlin.math.roundToInt

@Composable
fun AuthScreen(
    navController: NavController, authViewModel: AuthViewModel, mainViewModel: MainViewModel
) {

    val context = LocalContext.current
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var isPasswordShowing by remember { mutableStateOf(false) }
    var isLogin by remember { mutableStateOf(false) }
    val userResponse by authViewModel.userResponse.collectAsState()
    val checkEmail by authViewModel.checkEmailResponse.collectAsState()
    var isLoading by remember { mutableStateOf(false) }
    val url = "https://book-bank-dashboard.vercel.app/student"
    val listState = rememberLazyListState()
    var animatedOverscrollAmount by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(userResponse) {
        when (userResponse) {
            is NetworkResult.Loading -> {
                isLoading = true
            }

            is NetworkResult.Success -> {
                isLoading = false
                when (userResponse.data) {
                    is LoginResponse -> {
                        val data = userResponse.data as LoginResponse
                        Log.d("Ahsan", "AuthScreen: ${data.data}")
                        Log.d("Ahsan", "AuthScreen: ${data.jwt}")
                        mainViewModel.saveUserData(data.data)
                        mainViewModel.saveUserToken(data.jwt)
                    }
                }

            }

            is NetworkResult.Error -> {
                isLoading = false
                Toast.makeText(context, userResponse.error.toString(), Toast.LENGTH_SHORT).show()
            }

            else -> {
                // Handle Idle or other states
                isLoading = false
            }
        }
    }

    LaunchedEffect(checkEmail) {
        when (checkEmail) {
            is NetworkResult.Loading -> {
                isLoading = true
            }

            is NetworkResult.Success -> {
                isLoading = false
                navController.navigate(
                    Route.SetProfileScreen.setUserData(
                        emailText, passwordText
                    )
                )
                authViewModel.resetCheckEmailState()
                isLogin = true
            }

            is NetworkResult.Error -> {
                isLoading = false
                Toast.makeText(context, checkEmail.error.toString(), Toast.LENGTH_SHORT).show()
            }

            else -> {
                // Handle Idle or other states
                isLoading = false
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(MediumPadding1)

    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .customOverscroll(
                    listState,
                    onNewOverscrollAmount = { animatedOverscrollAmount = it }
                )
                .offset { IntOffset(0, animatedOverscrollAmount.roundToInt()) },
        ) {
            item {
                Image(
                    painter = painterResource(R.drawable.mainlogo),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(250.dp)
                )

                Spacer(Modifier.height(SmallPadding))

                Row {
                    Text(
                        "Email", style = TextStyle(
                            fontSize = 20.sp, fontFamily = interRegular
                        )
                    )
                    Spacer(Modifier.weight(1f))
                }

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
                Row {
                    Text(
                        "Password", style = TextStyle(
                            fontSize = 20.sp, fontFamily = interRegular
                        )
                    )
                    Spacer(Modifier.weight(1f))
                }

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

                Row {
                    Spacer(Modifier.weight(1f))
                    Text(
                        "Forgot Password?", style = TextStyle(
                            fontSize = 17.sp, fontFamily = interRegular, color = blueColor
                        ), modifier = Modifier

                            .clickable {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                context.startActivity(intent)
                            }
                    )
                }

                Spacer(Modifier.height(MediumPadding2))

                CustomButton(
                    text = if (isLogin) "Sign In" else "Sign Up",
                    color = buttonColor,
                    textSize = 17,
                    textColor = Color.White,
                    isLoading = isLoading,
                    radius = 8,
                    height = 70,
                    modifier = Modifier
                ) {
                    emailError = null
                    passwordError = null
                    val isEmailValid = isValidEmail(emailText)
                    val isPasswordValid = isValidPassword(passwordText)
                    if (!isEmailValid) {
                        emailError = "Please enter valid email"
                        return@CustomButton
                    }
                    if (isPasswordValid != null) {
                        passwordError = isPasswordValid
                        return@CustomButton
                    }

                    isLoading = true

                    if (!isLogin) {
                        authViewModel.checkEmail(CheckEmailRequest(email = emailText))

//                    navController.navigate(
//                        Route.SetProfileScreen.setUserData(
//                            emailText, passwordText
//                        )
//                    )
                    } else {
                        authViewModel.loginUser(
                            LoginRequest(
                                email = emailText, password = passwordText
                            )
                        )
                    }

                }

                Spacer(Modifier.height(SmallPadding))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        if (isLogin) "Don't have an account?" else "Already have an account?",
                        style = TextStyle(
                            fontSize = 17.sp, fontFamily = interRegular, color = buttonColor
                        )
                    )

                    Spacer(Modifier.width(XSmallPadding))

                    Text(if (isLogin) "Sign Up" else "Sign In", style = TextStyle(
                        fontSize = 17.sp, fontFamily = interRegular, color = blueColor
                    ), modifier = Modifier.clickable {
                        isLogin = !isLogin
                    })
                }/*
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
                        ) { }*/

            }
        }
    }
}


