package com.example.bookbank.api

import com.example.bookbank.models.LoginRequest
import com.example.bookbank.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT

interface StudentApi {

    @PUT("database/students/update")
    suspend fun updateStudent(@Body loginRequest: LoginRequest) : Response<LoginResponse>


}