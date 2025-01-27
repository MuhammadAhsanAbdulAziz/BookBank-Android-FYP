package com.example.bookbank.views.notification.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbank.models.Notification
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.ui.theme.redColor
import com.example.bookbank.util.Dimens.SmallPadding
import com.example.bookbank.util.Dimens.XSmallPadding
import com.example.bookbank.util.Helper.getTimeAgo

@Composable
fun NotificationListItem(notification: Notification, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .padding(vertical = XSmallPadding)
            .shadow(elevation = 20.dp)
            .background(
                color = if (notification.messages.severity == "normal") Color.White else redColor,
                shape = RoundedCornerShape(8.dp)
            )
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))

            .padding(SmallPadding)
    ) {
        Column {
            Row {
                Text(
                    "Admin", style = TextStyle(
                        fontSize = 15.sp,
                        color = buttonColor,
                        fontFamily = interBold,
                        textAlign = TextAlign.Start
                    )
                )

                Spacer(Modifier.weight(1f))

                Text(
                    getTimeAgo(notification.updated_at), style = TextStyle(
                        fontSize = 15.sp,
                        color = buttonColor,
                        fontFamily = interRegular,
                        textAlign = TextAlign.Start
                    )
                )

            }

            Spacer(Modifier.height(SmallPadding))

            Text(
                notification.messages.text, style = TextStyle(
                    fontSize = 14.sp,
                    color = buttonColor,
                    fontFamily = interRegular,
                    textAlign = TextAlign.Start
                )
            )
        }
    }
}