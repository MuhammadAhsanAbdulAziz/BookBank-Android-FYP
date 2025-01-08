package com.example.bookbank.util.navgraph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookbank.views.donate.DonateScreen
import com.example.bookbank.views.HomeScreen
import com.example.bookbank.views.ProfileScreen
import com.example.bookbank.views.request.RequestScreen

@Composable
fun MainNavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Route.HomeScreen.route,

        ) {
        composable(
            route = Route.HomeScreen.route,
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
            HomeScreen()
        }

        composable(
            route = Route.DonateScreen.route,
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
            DonateScreen()
        }

        composable(
            route = Route.RequestScreen.route,
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
            RequestScreen()
        }

        composable(
            route = Route.ProfileScreen.route,
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
            ProfileScreen()
        }

    }
}
