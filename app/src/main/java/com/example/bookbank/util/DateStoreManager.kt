package com.example.bookbank.util

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.bookbank.util.Constants.APP_ENTRY
import com.example.bookbank.util.Constants.APP_LANGUAGE
import com.example.bookbank.util.Constants.MY_PREFERENCES
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = MY_PREFERENCES)

class DataStoreManager @Inject constructor(@ApplicationContext private val context: Context) {

    suspend fun saveAppLanguage(language : String) {
        context.dataStore.edit { preferences ->
            preferences[APP_LANGUAGE_KEY] = language
        }
    }

    fun readAppLanguage(): Flow<String> {
        return context.dataStore.data.map { pref ->
            pref[APP_LANGUAGE_KEY] ?: ""
        }
    }

    suspend fun saveThemeMode(isDarkMode: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[THEME_MODE_KEY] = isDarkMode
        }
    }

    fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { pref ->
            pref[APP_ENTRY_KEY] ?: false
        }
    }

    // Read theme mode (returns true for dark mode, false for light mode)
    fun readThemeMode(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[THEME_MODE_KEY] ?: true // default to dark mode
        }
    }


    companion object {
        val APP_ENTRY_KEY = booleanPreferencesKey(APP_ENTRY)
        val APP_LANGUAGE_KEY = stringPreferencesKey(APP_LANGUAGE)
        val THEME_MODE_KEY = booleanPreferencesKey("theme_mode")
    }

}

