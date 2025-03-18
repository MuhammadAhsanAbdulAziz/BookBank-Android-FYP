package com.example.bookbank.repository

import android.util.Log
import com.example.bookbank.api.AuthApi
import com.example.bookbank.models.CheckEmailRequest
import com.example.bookbank.models.LoginRequest
import com.example.bookbank.models.RegisterRequest
import com.example.bookbank.util.Helper.handleResponse
import com.example.bookbank.util.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authApi: AuthApi) {

    private val TAG = "authrepository"

    private val _userResponse = MutableStateFlow<NetworkResult<Any>>(NetworkResult.Idle())
    val userResponse: StateFlow<NetworkResult<Any>> = _userResponse.asStateFlow()

    private val _checkEmailResponse = MutableStateFlow<NetworkResult<Any>>(NetworkResult.Idle())
    val checkEmailResponse: StateFlow<NetworkResult<Any>> = _checkEmailResponse.asStateFlow()

    suspend fun loginUser(loginRequest: LoginRequest) {
        _userResponse.value = NetworkResult.Loading()
        val response = authApi.login(loginRequest)
        handleResponse(response,_userResponse)

    }

    suspend fun registerUser(registerRequest: RegisterRequest) {
        _userResponse.value = NetworkResult.Loading()
        val response = authApi.register(registerRequest)
        Log.d(TAG, "registerUser: ${response.body()}")
        handleResponse(response,_userResponse)

    }

    suspend fun checkEmail(checkEmailRequest: CheckEmailRequest) {
        _checkEmailResponse.value = NetworkResult.Loading()
        val response = authApi.checkEmail(checkEmailRequest)
        handleResponse(response,_checkEmailResponse)

    }

    fun resetState(){
        _userResponse.value = NetworkResult.Idle()
    }

    fun resetCheckEmailState() {
        _checkEmailResponse.value = NetworkResult.Idle() // Replace with your idle state
    }
}
