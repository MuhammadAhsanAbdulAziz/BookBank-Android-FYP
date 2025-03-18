package com.example.bookbank.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.os.StatFs
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.bookbank.R
import kotlinx.coroutines.flow.MutableStateFlow
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

object Helper {

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): String? {
        // Check if the password is at least 6 characters, has at least one letter and one number
        val hasLetter = password.any { it.isLetter() }
        val hasDigit = password.any { it.isDigit() }
        return if (!hasLetter) "Password should contain atleast one letter"
        else if (!hasDigit) "Password should contain atleast one digit"
        else if (password.length < 6) "Password length should be atleast 6" else null
    }

    fun isValidPhoneNumber(phoneNumber: String): String? {
        // Check if the phone number contains only digits
        val isNumeric = phoneNumber.all { it.isDigit() }
        // Check the length of the phone number
        val validLength = phoneNumber.length == 10

        return when {
            !isNumeric -> "Phone number should contain only digits"
            !validLength -> "Phone number should be exactly 10 digits"
            else -> null
        }
    }

    fun isValidCNIC(cnic: String): String? {
        // Check if the phone number contains only digits
        val isNumeric = cnic.all { it.isDigit() }
        // Check the length of the phone number
        val validLength = cnic.length == 13

        return when {
            !isNumeric -> "CNIC should contain only digits"
            !validLength -> "CNIC should be exactly 10 digits"
            else -> null
        }
    }

    fun openMailApp(
        context: Context,
        senderName: String,
    ) {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // Only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, arrayOf("bookbankicc@gmail.com")) // Receiver email
            putExtra(Intent.EXTRA_SUBJECT, "Support Request from $senderName")
            putExtra(
                Intent.EXTRA_TEXT,
                ""
            )
        }

        try {
            context.startActivity(Intent.createChooser(emailIntent, "Choose Email Client"))
        } catch (e: Exception) {
            Toast.makeText(
                context,
                context.getString(R.string.no_email_app_found), Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun getDateWithMonthsAdded(monthsToAdd: Long): String {
        // Get the current date
        val currentDate = LocalDate.now()
        // Add the specified number of months
        val newDate = currentDate.plusMonths(monthsToAdd)
        // Format the date to a readable string (e.g., yyyy-MM-dd)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return newDate.format(formatter)
    }


    fun <T : Any> handleResponse(response: Response<T>, responseData: MutableStateFlow<NetworkResult<Any>>) {
        if (response.isSuccessful && response.body() != null) {
            responseData.value = NetworkResult.Success(response.body()!!)
        } else if (response.errorBody() != null) {
            try {
                val errorBody = response.errorBody()?.charStream()?.readText()
                if (errorBody != null) {
                    val errorObj = JSONObject(errorBody)
                    val errorResponse = when {
                        errorObj.has("error") -> {
                            // Directly get the error string if it's not an object
                            NetworkResult.Error(errorObj.getString("error"))
                        }
                        else -> {
                            NetworkResult.Error("Unknown error: ${errorObj.toString()}")
                        }
                    }
                    responseData.value = NetworkResult.Error(errorResponse.error)
                } else {
                    responseData.value = NetworkResult.Error("No error body found")
                }
            } catch (e: JSONException) {
                responseData.value = NetworkResult.Error("Error parsing error body: ${e.localizedMessage}")
            }
        } else {
            responseData.value = NetworkResult.Error("Unknown network error occurred")
        }
    }



    fun getTimeAgo(dateTime: String): String {
        // Parse the given datetime string
        val formatter = DateTimeFormatter.ISO_DATE_TIME
        val currentDateTime = LocalDateTime.now(ZoneOffset.UTC)
        val parsedDateTime = LocalDateTime.parse(dateTime, formatter)

        // Calculate the difference in seconds
        val durationInSeconds = ChronoUnit.SECONDS.between(parsedDateTime, currentDateTime)

        return when {
            // If time is less than a minute ago
            durationInSeconds < 60 -> "${durationInSeconds}s ago"

            // If time is less than an hour ago
            durationInSeconds < 3600 -> {
                val minutes = durationInSeconds / 60
                "${minutes}m ago"
            }

            // If time is less than a day ago
            durationInSeconds < 86400 -> {
                val hours = durationInSeconds / 3600
                "${hours}h ago"
            }

            // If time is less than a week ago
            durationInSeconds < 604800 -> {
                val days = durationInSeconds / 86400
                "${days}d ago"
            }

            // If time is less than a month ago
            durationInSeconds < 2592000 -> {
                val weeks = durationInSeconds / 604800
                "${weeks}w ago"
            }

            // If time is less than a year ago
            durationInSeconds < 31536000 -> {
                val months = durationInSeconds / 2592000
                "${months}mo ago"
            }

            // If time is more than a year ago
            else -> {
                val years = durationInSeconds / 31536000
                "${years}y ago"
            }
        }
    }



}