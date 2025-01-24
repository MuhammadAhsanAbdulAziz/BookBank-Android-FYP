package com.example.bookbank.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookbank.models.UserData
import com.example.bookbank.util.DataStoreManager
import com.example.bookbank.util.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dataStoreManager: DataStoreManager) : ViewModel() {


    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.AuthScreen.route)
        private set

    init {
        viewModelScope.launch {
            dataStoreManager.readUserToken().collect { hasToken ->
                startDestination = if (hasToken.isNotEmpty()) {
                    Route.MainScreen.route
                } else {
                    Route.AuthScreen.route
                }
                delay(300)
                splashCondition = false
            }

        }
    }

    fun saveUserData(data: UserData?){
        viewModelScope.launch {
            dataStoreManager.saveUserData(data)
        }
    }

    fun readUserData(): Flow<UserData?> {
        return dataStoreManager.readUserData()
    }

    fun saveUserToken(token:String){
        viewModelScope.launch {
            dataStoreManager.saveUserToken(token)
        }
    }

    fun readUserToken(): Flow<String> {
        return dataStoreManager.readUserToken()
    }

}
