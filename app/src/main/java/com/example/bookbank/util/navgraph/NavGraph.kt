package com.example.bookbank.util.navgraph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookbank.viewmodels.AuthViewModel
import com.example.bookbank.viewmodels.MainViewModel
import com.example.bookbank.viewmodels.UtilViewModel
import com.example.bookbank.views.MainScreen
import com.example.bookbank.views.auth.AuthScreen
import com.example.bookbank.views.auth.OTPScreen
import com.example.bookbank.views.auth.SetProfileScreen

@Composable
fun NavGraph(startDestination: String,utilViewModel: UtilViewModel = UtilViewModel()) {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()
    val mainViewModel: MainViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination,

        ) {
        composable(
            route = Route.AuthScreen.route,
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left, tween(1000)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right, tween(1000)
                )
            },
        ) {
            AuthScreen(navController = navController,authViewModel = authViewModel,mainViewModel = mainViewModel)
        }

        composable(
            route = Route.SetProfileScreen.route,
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left, tween(1000)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right, tween(1000)
                )
            },
        ) {
            SetProfileScreen(navController = navController,authViewModel = authViewModel)
        }

        composable(
            route = Route.OTPScreen.route,
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left, tween(1000)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right, tween(1000)
                )
            },
        ) {
            OTPScreen(navController = navController)
        }

        composable(
            route = Route.MainScreen.route,
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left, tween(1000)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right, tween(1000)
                )
            },
        ) {
            MainScreen(utilViewModel = utilViewModel)
        }

    }
}
