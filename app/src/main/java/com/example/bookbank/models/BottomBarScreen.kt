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
    object Donate: BottomBarScreen(
        route = "donateScreen",
        title = "Donate",
        icon = R.drawable.volunteer_activism_24px,
        icon_focused = R.drawable.volunteer_activism_24px
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
