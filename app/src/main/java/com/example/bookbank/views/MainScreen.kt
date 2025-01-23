package com.example.bookbank.views

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bookbank.R
import com.example.bookbank.models.BottomBarScreen
import com.example.bookbank.ui.theme.appColor
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.util.navgraph.MainNavGraph
import com.example.bookbank.util.navgraph.Route
import com.example.bookbank.viewmodels.MainViewModel
import com.example.bookbank.viewmodels.UtilViewModel
import com.example.bookbank.views.dialog.CartDetailDialog
import com.example.bookbank.views.menus.CustomAppBar

@Composable
fun MainScreen(utilViewModel: UtilViewModel,mainViewModel: MainViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    var shouldShowBottomBar by remember { mutableStateOf(true) }
    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination


    LaunchedEffect(currentDestination) {
//        shouldShowBottomBar = currentDestination?.route != Route.AddLinkScreen.route
    }


    Scaffold(topBar = {
        CustomAppBar(utilViewModel = utilViewModel)
    },
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars),
        bottomBar = {
            if (shouldShowBottomBar) {
                BottomBar(navController = navController)
            }
        }) {
        Column(
            modifier = when (currentDestination?.route) {
                Route.HomeScreen.route -> Modifier
                    .fillMaxSize()
                    .paint(
                        // Replace with your image id
                        painterResource(id = R.drawable.bg), contentScale = ContentScale.FillBounds
                    )
                    .padding(it)

                Route.BookDetailScreen.route -> Modifier
                    .fillMaxSize()
                    .paint(
                        // Replace with your image id
                        painterResource(id = R.drawable.bg), contentScale = ContentScale.FillBounds
                    )
                    .padding(it)

                Route.ProfileScreen.route -> Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(it)

                else -> Modifier
                    .fillMaxSize()
                    .paint(
                        // Replace with your image id
                        painterResource(id = R.drawable.bg), contentScale = ContentScale.FillBounds
                    )
                    .padding(it)
            },


            ) {
            MainNavGraph(navController = navController, utilViewModel)

        }


    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Donate,
        BottomBarScreen.Request,
        BottomBarScreen.Profile
    )

    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        contentAlignment = Alignment.Center // Center the content of this Box
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .clip(CircleShape)
                .background(Color(0xFF1E1E1E))
                .height(75.dp)
        ) {
            Row(
                modifier = Modifier.padding(10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                screens.forEach { screen ->
                    AddItem(
                        screen = screen,
                        currentDestination = currentDestination,
                        navController = navController
                    )
                }
            }
        }
    }
}

//fun shouldShowBottomBar(navController: NavHostController): Boolean {
//    val currentRoute = navController.currentBackStackEntry?.destination?.route
//    return currentRoute != Route.AddLinkScreen.route && currentRoute != Route.DetailLinkScreen.route
//}

@Composable
fun AddItem(
    screen: BottomBarScreen, currentDestination: NavDestination?, navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    val background = if (selected) appColor else Color.Transparent

    val contentColor = if (selected) buttonColor else Color(0xFF949494)

    Box(
        modifier = Modifier
            .height(50.dp)
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {
        Row(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painter = painterResource(id = if (selected) screen.icon_focused else screen.icon),
                contentDescription = "icon",
                tint = contentColor,
                modifier = Modifier.size(40.dp)

            )
            AnimatedVisibility(visible = selected) {
                Text(
                    text = screen.title, style = TextStyle(
                        fontFamily = interBold, fontSize = 14.sp, color = contentColor
                    )
                )
            }
        }
    }
}