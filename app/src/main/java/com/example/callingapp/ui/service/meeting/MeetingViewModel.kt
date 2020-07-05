package com.example.callingapp.ui.service.meeting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.Member
import com.example.repository.MemberRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MeetingViewModel @Inject constructor(
    private val memberRepository: MemberRepository
) : ViewModel() {

    private val _membersList: MediatorLiveData<List<Member>> =
        MediatorLiveData<List<Member>>().apply {
            addSource(memberRepository.searchResult()) {
                value = it
            }
        }

    val membersList: LiveData<List<Member>> = _membersList

    fun fetch() {
        viewModelScope.launch {
            _membersList.value = memberRepository.findAll()
            Timber.v("result: ${membersList.value}")
        }
    }
}
