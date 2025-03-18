package com.example.bookbank.models

import com.example.bookbank.R


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int
) {

    // for home
    object Home: BottomBarScreen(
        route = "homeScreen",
        title = "Home",
        icon = R.drawable.home_24px,
        icon_focused = R.drawable.home_24px
    )

    // for report
    object Support: BottomBarScreen(
        route = "donateScreen",
        title = "Support",
        icon = R.drawable.contact,
        icon_focused = R.drawable.contact
    )

    object Notification: BottomBarScreen(
        route = "notificationScreen",
        title = "Notification",
        icon = R.drawable.notis,
        icon_focused = R.drawable.notis
    )

    // for report
    object Request: BottomBarScreen(
        route = "requestScreen",
        title = "Request",
        icon = R.drawable.mail,
        icon_focused = R.drawable.mail
    )

    // for report
    object Profile: BottomBarScreen(
        route = "profileScreen",
        title = "Profile",
        icon = R.drawable.person_24px,
        icon_focused = R.drawable.person_24px
    )

}
