package com.example.callingapp.ext

import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    this.activity?.currentFocus?.let {
        val manager = activity?.getSystemService<InputMethodManager>()
        manager?.hideSoftInputFromWindow(it.windowToken, 0)
    }
}
