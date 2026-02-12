package com.example.saferecycle.data.network

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext private val context: Context) {
    private val prefs =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        prefs.edit { putString("AUTH_TOKEN", token) }
    }

    fun saveRefreshToken(token: String) {
        prefs.edit { putString("AUTH_REFRESH_TOKEN", token) }
    }

    fun getToken(): String? =
        prefs.getString("AUTH_TOKEN", null)

    fun getRefreshToken(): String? =
        prefs.getString("AUTH_REFRESH_TOKEN", null)

    fun clear() {
        prefs.edit { clear() }
    }
}