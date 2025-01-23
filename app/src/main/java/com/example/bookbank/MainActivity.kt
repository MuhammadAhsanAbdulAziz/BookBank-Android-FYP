package com.example.bookbank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.bookbank.ui.theme.BookBankTheme
import com.example.bookbank.ui.theme.appColor
import com.example.bookbank.util.navgraph.NavGraph
import com.example.bookbank.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.splashCondition
            }
        }

        setContent {
            BookBankTheme {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.light(
                        appColor.toArgb(), appColor.toArgb(),
                    ),
                )
                Box(modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars)) {
                    val startDes = viewModel.startDestination
                    NavGraph(startDestination = startDes)
                }
            }
        }
    }
}
