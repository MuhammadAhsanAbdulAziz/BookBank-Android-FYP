package com.example.bookbank.api

import com.example.bookbank.models.LoginRequest
import com.example.bookbank.models.LoginResponse
import com.example.bookbank.models.RegisterRequest
import com.example.bookbank.models.SuccessResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("mobile/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest) : Response<LoginResponse>

    @POST("mobile/auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest) : Response<SuccessResponse>


}