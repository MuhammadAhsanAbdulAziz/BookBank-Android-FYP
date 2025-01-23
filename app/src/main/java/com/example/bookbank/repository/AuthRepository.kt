package com.example.bookbank.repository

import android.util.Log
import com.example.bookbank.api.AuthApi
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

    private val _userResponse = MutableStateFlow<NetworkResult<Any>>(NetworkResult.Idle())
    val userResponse: StateFlow<NetworkResult<Any>> = _userResponse.asStateFlow()

    suspend fun loginUser(loginRequest: LoginRequest) {
        _userResponse.value = NetworkResult.Loading()
        val response = authApi.login(loginRequest)
        handleResponse(response,_userResponse)

    }

    suspend fun registerUser(registerRequest: RegisterRequest) {
        _userResponse.value = NetworkResult.Loading()
        val response = authApi.register(registerRequest)
        handleResponse(response,_userResponse)

    }
}
