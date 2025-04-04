package com.example.bookbank.views.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bookbank.R
import com.example.bookbank.models.RegisterRequest
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.Dimens.MediumPadding2
import com.example.bookbank.util.Dimens.SmallPadding
import com.example.bookbank.util.Helper.isValidCNIC
import com.example.bookbank.util.Helper.isValidEmail
import com.example.bookbank.util.Helper.isValidPassword
import com.example.bookbank.util.Helper.isValidPhoneNumber
import com.example.bookbank.util.NetworkResult
import com.example.bookbank.util.customOverscroll
import com.example.bookbank.viewmodels.AuthViewModel
import com.example.bookbank.views.common.CustomButton
import com.example.bookbank.views.common.CustomTextField
import kotlin.math.roundToInt

@Composable
fun RegisterScreen(
    navController: NavController, authViewModel: AuthViewModel, modifier: Modifier = Modifier
) {

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
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var isPasswordShowing by remember { mutableStateOf(false) }
    val userResponse by authViewModel.userResponse.collectAsState()
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val phoneMaxLength = 10
    val cnicMaxLength = 13
    val listState = rememberLazyListState()
    var animatedOverscrollAmount by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(userResponse) {
        when (userResponse) {
            is NetworkResult.Loading -> {
                isLoading = true
            }

            is NetworkResult.Success -> {
                isLoading = false
                Toast.makeText(context, "Account created successfully", Toast.LENGTH_SHORT).show()
                authViewModel.resetState()
                navController.popBackStack()

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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(MediumPadding1)

    ) {
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .customOverscroll(
                    listState,
                    onNewOverscrollAmount = { animatedOverscrollAmount = it }
                )
                .offset { IntOffset(0, animatedOverscrollAmount.roundToInt()) },

            ) {
            item {
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

                Row {
                    Text(
                        "Name", style = TextStyle(
                            fontSize = 20.sp, fontFamily = interRegular
                        )
                    )
                    Spacer(Modifier.weight(1f))
                }

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

                Row {
                    Text(
                        "Father's Name", style = TextStyle(
                            fontSize = 20.sp, fontFamily = interRegular
                        )
                    )
                    Spacer(Modifier.weight(1f))
                }

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
                    onValueChange = {
                        emailText = it
                    })

                Spacer(Modifier.height(MediumPadding2))

                Row {
                    Text(
                        "Address",
                        style = TextStyle(
                            fontSize = 20.sp, fontFamily = interRegular
                        ),
                    )
                    Spacer(Modifier.weight(1f))
                }

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

                Row {
                    Text(
                        "Phone No", style = TextStyle(
                            fontSize = 20.sp, fontFamily = interRegular
                        )
                    )
                    Spacer(Modifier.weight(1f))
                }

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

                Row {
                    Text(
                        "CNIC", style = TextStyle(
                            fontSize = 20.sp, fontFamily = interRegular
                        )
                    )
                    Spacer(Modifier.weight(1f))
                }

                Spacer(Modifier.height(SmallPadding))

                CustomTextField(modifier = Modifier,
                    text = "Enter your CNIC",
                    bgColor = Color.White,
                    borderColor = buttonColor,
                    textColor = buttonColor,
                    textValue = cnicText,
                    borderRad = 5,
                    keyboardType = KeyboardType.Number,
                    error = cnicError,

                    onValueChange = {
                        if (it.length <= cnicMaxLength) cnicText = it
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

                Spacer(Modifier.height(MediumPadding2))

                CustomButton(
                    text = "Register",
                    color = buttonColor,
                    textSize = 17,
                    textColor = Color.White,
                    isLoading = isLoading,
                    radius = 8,
                    height = 70,
                    modifier = Modifier
                ) {
                    nameError = null
                    fNameError = null
                    addressError = null
                    phoneError = null
                    cnicError = null
                    emailError = null
                    passwordError = null

                    val isEmailValid = isValidEmail(emailText)
                    val isPasswordValid = isValidPassword(passwordText)
                    val numError = isValidPhoneNumber(phoneText)

                    if (nameText.isEmpty()) {
                        nameError = "Please enter your name"
                        return@CustomButton
                    }
                    if (fNameText.isEmpty()) {
                        fNameError = "Please enter your father's name"
                        return@CustomButton
                    }

                    if (!isEmailValid) {
                        emailError = "Please enter valid email"
                        return@CustomButton
                    }
                    if (addressText.isEmpty()) {
                        addressError = "Please enter your address"
                        return@CustomButton
                    }

                    if (!numError.isNullOrEmpty()) {
                        phoneError = numError
                        return@CustomButton
                    }
                    val cNICError = isValidCNIC(cnicText)
                    if (!cNICError.isNullOrEmpty()) {
                        cnicError = cNICError
                        return@CustomButton
                    }
                    if (isPasswordValid != null) {
                        passwordError = isPasswordValid
                        return@CustomButton
                    }

                    isLoading = true

                    authViewModel.registerUser(
                        RegisterRequest(
                            address = addressText,
                            cnic = cnicText,
                            email = emailText,
                            father_name = fNameText,
                            mobile = "+92$phoneText",
                            name = nameText,
                            password = passwordText
                        )
                    )

                }
            }
        }
    }
}