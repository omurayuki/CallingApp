package com.example.callingapp.ui.admin.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.callingapp.ext.combine
import com.example.preferences.Preferences
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    val password = MutableLiveData<String>("")

    val enable = combine(false, password) { _, password ->
        password.isNotEmpty()
    }

    fun testPassword(): Boolean = password.value == preferences.adminPassword
}
