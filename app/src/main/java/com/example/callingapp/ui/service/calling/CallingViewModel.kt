package com.example.callingapp.ui.service.calling

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.delay
import javax.inject.Inject

class CallingViewModel @Inject constructor() : ViewModel() {
    val finish: LiveData<Unit> = liveData {
        delay(4000)
        emit(Unit)
    }
}
