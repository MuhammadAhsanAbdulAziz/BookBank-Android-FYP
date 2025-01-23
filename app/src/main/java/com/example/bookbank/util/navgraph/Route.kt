package com.example.bookbank.util.navgraph

import android.net.Uri

sealed class Route(val route: String) {

    object AuthScreen : Route(route = "authScreen")
    object SetProfileScreen : Route(route = "setProfileScreen/{email}/{password}") {
        fun setUserData(email: String, password: String) = "setProfileScreen/$email/$password"
    }

    object OTPScreen : Route(route = "otpScreen")
    object MainScreen : Route(route = "mainScreen")
    object HomeScreen : Route(route = "homeScreen")
    object BookDetailScreen : Route(route = "bookDetailScreen/{book}"){
        fun setBookData(book: String): String {
            return "bookDetailScreen/${Uri.encode(book)}" // Encode the JSON string
        }
    }
    object DonateScreen : Route(route = "donateScreen")
    object RequestScreen : Route(route = "requestScreen")
    object ProfileScreen : Route(route = "profileScreen")
    object UpdateProfileScreen : Route(route = "updateProfileScreen")
}