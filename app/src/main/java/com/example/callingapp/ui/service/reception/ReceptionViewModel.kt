package com.example.callingapp.ui.service.reception

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.callingapp.ext.combine
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class ReceptionViewModel @Inject constructor(): ViewModel() {
    val corporateName = MutableLiveData<String>().apply { value = "" }
    val name = MutableLiveData<String>().apply { value = "" }
    val purpose = MutableLiveData<String>().apply { value = "" }

    val enable = combine(false, corporateName, name) { _, corporate, name ->
        corporate.isNotEmpty() && name.isNotEmpty()
    }
}
