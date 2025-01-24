package com.example.bookbank.views.notification

import android.util.Log
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.NetworkResult
import com.example.bookbank.viewmodels.StudentViewModel
import com.example.bookbank.views.notification.common.NotificationListItem

@Composable
fun NotificationScreen(
    studentViewModel: StudentViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val notificationResponse = studentViewModel.notificationResponse.collectAsState()
    val notifications = studentViewModel.notifications.collectAsState()


    LaunchedEffect(Unit) {
        studentViewModel.getNotification()
    }


    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier
                .fillMaxWidth()
                .padding(MediumPadding1)
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
                    LazyColumn(

                    ) {
                        items(notifications.value.size) { index ->
                            NotificationListItem(notification = notifications.value[index])
                        }
                    }
                    Log.d("Err", "HomeScreen: Fine")
                }

                else -> {
                    Log.d("Err", "HomeScreen: Else")
                }
            }

        }
    }
}