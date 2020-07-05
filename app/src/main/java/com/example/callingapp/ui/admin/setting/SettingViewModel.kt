package com.example.callingapp.ui.admin.setting

import androidx.lifecycle.ViewModel
import com.example.preferences.Preferences
import javax.inject.Inject

class SettingViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    var newPassword: String? = null

    var newSlackChannel: String? = null

    val currentSlackChannel: String
        get() = preferences.slackChannel

    fun save() {
        newPassword.takeIf { !it.isNullOrEmpty() }?.let {
            preferences.adminPassword = it
        }
        newSlackChannel.takeIf { !it.isNullOrEmpty() }?.let {
            preferences.slackChannel = it
        }
    }
}
