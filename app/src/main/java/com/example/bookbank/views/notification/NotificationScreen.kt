package com.example.bookbank.views.notification

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.bookbank.R
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.Dimens.SmallPadding
import com.example.bookbank.util.NetworkResult
import com.example.bookbank.viewmodels.StudentViewModel
import com.example.bookbank.views.notification.common.NotificationListItem

@Composable
fun NotificationScreen(
    studentViewModel: StudentViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.notification))
    val notificationResponse = studentViewModel.notificationResponse.collectAsState()
    val notifications = studentViewModel.notifications.collectAsState()
    val progress by animateLottieCompositionAsState(
        composition = composition, iterations = LottieConstants.IterateForever // Infinite loop
    )

    LaunchedEffect(Unit) {
        studentViewModel.getNotification()
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(SmallPadding)
    ) {

        Column(
            modifier
                .fillMaxWidth()
        ) {
            Text(
                "Notifications", style = TextStyle(
                    fontSize = 20.sp,
                    color = buttonColor,
                    fontFamily = interBold,
                    textAlign = TextAlign.Start
                )
            )

            Spacer(Modifier.height(MediumPadding1))

            when (notificationResponse.value) {
                is NetworkResult.Loading -> {
                    CircularProgressIndicator(
                        color = buttonColor,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Log.d("Err", "HomeScreen: Loading")
                }

                is NetworkResult.Error -> {
                    Log.d("Err", "HomeScreen: ${notificationResponse.value.error}")
                }

                is NetworkResult.Success -> {
                    if (notifications.value.isEmpty()) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Spacer(Modifier.weight(1f))

                            LottieAnimation(
                                contentScale = ContentScale.Crop,
                                composition = composition,
                                progress = { progress },
                            )

                            Text(
                                "No Notifications yet", style = TextStyle(
                                    fontSize = 15.sp,
                                    color = buttonColor,
                                    fontFamily = interRegular,
                                    textAlign = TextAlign.Start
                                )
                            )

                            Spacer(Modifier.weight(1f))

                        }
                    } else {
                        LazyColumn {
                            items(notifications.value.size) { index ->
                                NotificationListItem(notification = notifications.value[index])
                            }
                        }
                    }
                    Log.d("Err", "HomeScreen: Fine")
                }

                else -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        LottieAnimation(
                            contentScale = ContentScale.Crop,
                            composition = composition,
                            progress = { progress },
                        )

                        Text(
                            "No Notifications yet", style = TextStyle(
                                fontSize = 15.sp,
                                color = buttonColor,
                                fontFamily = interRegular,
                                textAlign = TextAlign.Start
                            )
                        )

                    }
                }
            }

        }
    }
}