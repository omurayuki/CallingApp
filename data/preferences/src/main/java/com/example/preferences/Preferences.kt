package com.example.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class Preferences @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    companion object {
        private const val ADMIN_PASSWORD = "admin_password"
        private const val SLACK_CHANNEL = "slack_channel"
    }

    var adminPassword: String
        get() = sharedPreferences.getString(ADMIN_PASSWORD, null) ?: "1212"
        set(value) = sharedPreferences.edit { putString(ADMIN_PASSWORD, value) }

    var slackChannel: String
        get() = sharedPreferences.getString(SLACK_CHANNEL, null) ?: ""
        set(value) = sharedPreferences.edit { putString(SLACK_CHANNEL, value) }
}
