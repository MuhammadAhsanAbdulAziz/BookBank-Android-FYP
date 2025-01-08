package com.example.bookbank.util.navgraph

sealed class Route(val route: String) {

    object AuthScreen : Route(route = "authScreen")
    object SetProfileScreen : Route(route = "setProfileScreen")
    object OTPScreen : Route(route = "otpScreen")
    object MainScreen : Route(route = "mainScreen")
    object HomeScreen : Route(route = "homeScreen")
    object BookDetailScreen : Route(route = "bookDetailScreen")
    object DonateScreen : Route(route = "donateScreen")
    object RequestScreen : Route(route = "requestScreen")
    object ProfileScreen : Route(route = "profileScreen")
}