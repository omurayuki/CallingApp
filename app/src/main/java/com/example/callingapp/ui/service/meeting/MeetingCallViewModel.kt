package com.example.callingapp.ui.service.meeting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.preferences.Preferences
import com.example.repository.SlackRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MeetingCallViewModel @Inject constructor(
    private val slackRepository: SlackRepository,
    private val preferences: Preferences
): ViewModel() {

    fun call(text: String) {
        viewModelScope.launch {
            try {
                slackRepository.post(preferences.slackChannel, text)
            } catch (e: Exception) {
                Timber.e(e, "Something wrong for slack api call.")
            }
        }
    }
}
