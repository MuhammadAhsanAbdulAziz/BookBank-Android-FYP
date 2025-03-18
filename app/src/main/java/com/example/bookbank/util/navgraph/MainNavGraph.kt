package com.example.bookbank.util.navgraph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bookbank.models.BookData
import com.example.bookbank.viewmodels.BookViewModel
import com.example.bookbank.viewmodels.MainViewModel
import com.example.bookbank.viewmodels.UtilViewModel
import com.example.bookbank.views.donate.SupportScreen
import com.example.bookbank.views.home.HomeScreen
import com.example.bookbank.views.home.common.BookDetailScreen
import com.example.bookbank.views.notification.NotificationScreen
import com.example.bookbank.views.profile.ProfileScreen
import com.example.bookbank.views.profile.common.UpdateProfile
import com.example.bookbank.views.request.RequestScreen
import com.google.gson.Gson

@Composable
fun MainNavGraph(
    navController: NavHostController, utilViewModel: UtilViewModel,
    bookViewModel: BookViewModel
) {

    val mainViewModel: MainViewModel = hiltViewModel()

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
            HomeScreen(
                navController = navController,
                bookViewModel = bookViewModel
            )
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
            SupportScreen(utilViewModel = utilViewModel,mainViewModel = mainViewModel)
        }

        composable(
            route = Route.NotificationScreen.route,
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
            NotificationScreen()
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
            RequestScreen(bookViewModel = bookViewModel)
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
            ProfileScreen(navController = navController, mainViewModel = mainViewModel)
        }

        composable(
            route = Route.BookDetailScreen.route,
            arguments = listOf(
                navArgument("book") {
                    type = NavType.StringType
                } // Define "book" as a String argument
            ),
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
        ) { backStackEntry ->
            val bookData =
                backStackEntry.arguments?.getString("book") // Retrieve the "book" argument
            val gson = Gson()
            val book = gson.fromJson(bookData, BookData::class.java) // Deserialize the book data

            BookDetailScreen(
                navController = navController,
                utilViewModel = utilViewModel,
                bookViewModel = bookViewModel,
                book = book // Pass the book object to the screen
            )
        }

        composable(
            route = Route.UpdateProfileScreen.route,
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
            UpdateProfile(navController = navController, mainViewModel = mainViewModel)
        }

    }
}
