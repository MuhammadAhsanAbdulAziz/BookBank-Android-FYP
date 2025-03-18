package com.example.bookbank.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookbank.models.CheckEmailRequest
import com.example.bookbank.models.LoginRequest
import com.example.bookbank.models.RegisterRequest
import com.example.bookbank.repository.AuthRepository
import com.example.bookbank.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    // Exposing the StateFlow to the UI, not allowing modification outside
    val userResponse: StateFlow<NetworkResult<Any>> = authRepository.userResponse
    val checkEmailResponse: StateFlow<NetworkResult<Any>> = authRepository.checkEmailResponse

    fun loginUser(loginRequest: LoginRequest) {
        viewModelScope.launch {
            authRepository.loginUser(loginRequest)
        }
    }

    fun registerUser(registerRequest: RegisterRequest) {
        Log.d("Ahsan", "registerUser: $registerRequest")
        viewModelScope.launch {
            authRepository.registerUser(registerRequest)
        }
    }

    fun resetState(){
        authRepository.resetState()
    }

    fun checkEmail(checkEmailRequest: CheckEmailRequest) {
        viewModelScope.launch {
            authRepository.checkEmail(checkEmailRequest)
        }
    }

    fun resetCheckEmailState() {
        authRepository.resetCheckEmailState()
    }



}