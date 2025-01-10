package com.example.bookbank.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookbank.util.DataStoreManager
import com.example.bookbank.util.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(dataStoreManager: DataStoreManager) : ViewModel() {

    var startDestination by mutableStateOf(Route.AuthScreen.route)
        private set

    init {
        viewModelScope.launch {
//            dataStoreManager.readAppLanguage().collect { hasLanguage ->
//                if (hasLanguage.isNotEmpty()) {
//                    dataStoreManager.readAppEntry().collect { hasEntered ->
//                        startDestination = if (hasEntered) {
//                            Route.MainNavigation.route
//                        } else {
//                            Route.AppStartNavigation2.route
//                        }
//                    }
//                }
//                else {
//                    startDestination = Route.AuthScreen.route
//                }
//            }

            startDestination = Route.MainScreen.route
        }
    }
}
