package com.example.bookbank.util

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.bookbank.models.LoginResponse
import com.example.bookbank.models.UserData
import com.example.bookbank.util.Constants.APP_ENTRY
import com.example.bookbank.util.Constants.MY_PREFERENCES
import com.example.bookbank.util.Constants.USER_DATA
import com.example.bookbank.util.Constants.USER_TOKEN
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = MY_PREFERENCES)

class DataStoreManager @Inject constructor(@ApplicationContext private val context: Context) {

    private val gson = Gson()

    suspend fun saveUserToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY] = token
        }
    }

    fun readUserToken(): Flow<String> {
        return context.dataStore.data.map { pref ->
            pref[USER_TOKEN_KEY] ?: ""
        }
    }

    suspend fun saveUserData(data: UserData?) {
        context.dataStore.edit { preferences ->
            val jsonString = gson.toJson(data) // Serialize to JSON
            preferences[USER_DATA_KEY] = jsonString
        }
    }

    fun readUserData(): Flow<UserData?> {
        return context.dataStore.data.map { preferences ->
            val jsonString = preferences[USER_DATA_KEY]
            jsonString?.let { gson.fromJson(it, UserData::class.java) } // Deserialize JSON
        }
    }

    companion object {
        val APP_ENTRY_KEY = booleanPreferencesKey(APP_ENTRY)
        val USER_TOKEN_KEY = stringPreferencesKey(USER_TOKEN)
        val USER_DATA_KEY = stringPreferencesKey(USER_DATA)
        val THEME_MODE_KEY = booleanPreferencesKey("theme_mode")
    }

}

