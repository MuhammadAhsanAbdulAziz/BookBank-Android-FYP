package com.example.bookbank.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.os.StatFs
import android.util.Log
import android.widget.Toast
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

    fun openMailApp(context: Context, senderEmail: String, senderName: String, bodyMessage: String) {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // Only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, arrayOf("info@webpromayo.com")) // Receiver email
            putExtra(Intent.EXTRA_SUBJECT, "Support Request from $senderName")
            putExtra(
                Intent.EXTRA_TEXT,
                "Name: $senderName\nEmail: $senderEmail\n\nMessage:\n$bodyMessage"
            )
        }

        try {
            context.startActivity(Intent.createChooser(emailIntent, "Choose Email Client"))
        } catch (e: Exception) {
            Toast.makeText(context, "No email app found", Toast.LENGTH_SHORT).show()
        }
    }


    fun updateLocale(context: Context,newLocale: Locale) {
        val configuration = context.resources.configuration
        configuration.setLocale(newLocale)
    }


}