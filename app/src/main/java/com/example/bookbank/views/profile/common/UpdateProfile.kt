package com.example.bookbank.views.profile.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.bookbank.R
import com.example.bookbank.ui.theme.appColor
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.Dimens.XSmallPadding
import com.example.bookbank.util.Dimens.XXSmallPadding
import com.example.bookbank.viewmodels.MainViewModel
import com.example.bookbank.views.common.CustomButton
import com.example.bookbank.views.common.CustomTextField

@Composable
fun UpdateProfile(navController: NavController, mainViewModel: MainViewModel, modifier: Modifier = Modifier) {

    val userData by mainViewModel.readUserData().collectAsState(initial = null)

    val nameText = remember { mutableStateOf("") }
    val fNameText = remember { mutableStateOf("") }
    val addressText = remember { mutableStateOf("") }

    LaunchedEffect(userData) {
        userData?.let {
            nameText.value = it.name
            fNameText.value = it.father_name
            addressText.value = it.address
        }
    }



    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(MediumPadding1),
        ) {
            item{

                Row {

                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)
                    }

                    Spacer(Modifier.weight(1f))

                    Row {
                        Image(
                            painter = painterResource(R.drawable.placeholder),
                            contentDescription = null,
                            modifier = Modifier
                                .size(140.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.Crop
                        )
/*
                        IconButton(
                            onClick = {

                            },
                            modifier = Modifier
                                .offset(
                                    y = (110).dp, x = (-20).dp
                                ) // Negative offset to move the row upwards
                                .zIndex(1f)
                                .background(
                                    color = appColor,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .size(30.dp)

                        ) {
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = null,
                                tint = buttonColor

                            )
                        }*/

                    }

                    Spacer(Modifier.weight(2f))
                }

                Spacer(Modifier.height(MediumPadding1))

                Text(
                    "Full Name", style = TextStyle(
                        fontSize = 16.sp, color = buttonColor, fontFamily = interBold, textAlign = TextAlign.Start
                    )
                )

                Spacer(Modifier.height(XSmallPadding))

                CustomTextField(
                    modifier = Modifier,
                    text = nameText.value,
                    bgColor = Color.White,
                    borderColor = Color.Black,
                    textColor = Color.Black,
                    textValue = nameText.value,
                    borderRad = 5,
                    keyboardType = KeyboardType.Text,
                ) {
                    nameText.value = it
                }

                Spacer(Modifier.height(MediumPadding1))

                Text(
                    "Father's Name", style = TextStyle(
                        fontSize = 16.sp, color = buttonColor, fontFamily = interBold, textAlign = TextAlign.Start
                    )
                )

                Spacer(Modifier.height(XSmallPadding))

                CustomTextField(
                    modifier = Modifier,
                    text = fNameText.value,
                    bgColor = Color.White,
                    borderColor = Color.Black,
                    textColor = Color.Black,
                    textValue = fNameText.value,
                    borderRad = 5,
                    keyboardType = KeyboardType.Text,
                ) {
                    fNameText.value = it
                }

                Spacer(Modifier.height(MediumPadding1))

                Text(
                    "Address Name", style = TextStyle(
                        fontSize = 16.sp, color = buttonColor, fontFamily = interBold, textAlign = TextAlign.Start
                    )
                )

                Spacer(Modifier.height(XSmallPadding))

                CustomTextField(
                    modifier = Modifier,
                    text = addressText.value,
                    bgColor = Color.White,
                    borderColor = Color.Black,
                    textColor = Color.Black,
                    textValue = addressText.value,
                    borderRad = 5,
                    keyboardType = KeyboardType.Text,
                ) {
                    addressText.value = it
                }

                Spacer(Modifier.height(MediumPadding1))
                
                Row {

                    Spacer(Modifier.weight(1f))

                    CustomButton(
                        text = "Save",
                        color = buttonColor,
                        textSize = 16,
                        textColor = Color.White,
                        isLoading = false,
                        radius = 7,
                        height = 50,
                        modifier = Modifier.fillMaxWidth(.5f)
                    ) {

                    }


                    Spacer(Modifier.weight(1f))
                }

                Spacer(Modifier.height(MediumPadding1))

            }
        }

    }
}