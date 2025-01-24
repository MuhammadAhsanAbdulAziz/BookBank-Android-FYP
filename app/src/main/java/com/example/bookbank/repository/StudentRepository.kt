package com.example.bookbank.repository

import com.example.bookbank.api.StudentApi
import com.example.bookbank.models.UpdateStudentRequest
import com.example.bookbank.util.Helper.handleResponse
import com.example.bookbank.util.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class StudentRepository @Inject constructor(private val studentApi: StudentApi) {

    private val _userResponse = MutableStateFlow<NetworkResult<Any>>(NetworkResult.Idle())
    val userResponse: StateFlow<NetworkResult<Any>> = _userResponse.asStateFlow()

    private val _notificationResponse = MutableStateFlow<NetworkResult<Any>>(NetworkResult.Idle())
    val notificationResponse: StateFlow<NetworkResult<Any>> = _notificationResponse.asStateFlow()

    suspend fun updateStudent(updateStudentRequest: UpdateStudentRequest) {
        _userResponse.value = NetworkResult.Loading()
        val response = studentApi.updateStudent(updateStudentRequest)
        handleResponse(response, _userResponse)

    }

    suspend fun getNotifications() {
        _userResponse.value = NetworkResult.Loading()
        val response = studentApi.getNotifications()
        handleResponse(response, _notificationResponse)

    }

}
