package com.example.bookbank.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookbank.models.BookData
import com.example.bookbank.models.BooksResponse
import com.example.bookbank.models.LoginRequest
import com.example.bookbank.models.Notification
import com.example.bookbank.models.NotificationResponse
import com.example.bookbank.models.RegisterRequest
import com.example.bookbank.models.UpdateStudentRequest
import com.example.bookbank.repository.AuthRepository
import com.example.bookbank.repository.StudentRepository
import com.example.bookbank.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(
    private val studentRepository: StudentRepository
) : ViewModel() {

    // Exposing the StateFlow to the UI, not allowing modification outside
    val userResponse: StateFlow<NetworkResult<Any>> = studentRepository.userResponse
    val notificationResponse: StateFlow<NetworkResult<Any>> = studentRepository.notificationResponse

    private val _notifications = MutableStateFlow<List<Notification>>(emptyList())
    val notifications: StateFlow<List<Notification>> = _notifications.asStateFlow()

    fun updateStudent(updateStudentRequest: UpdateStudentRequest) {
        viewModelScope.launch {
            studentRepository.updateStudent(updateStudentRequest)
        }
    }

    fun getNotification() {
        viewModelScope.launch {
            studentRepository.getNotifications()
            if (notificationResponse.value is NetworkResult.Success) {
                val result = notificationResponse.value.data as NotificationResponse
                _notifications.value = result.notifications
            }
        }
    }

}