package com.example.callingapp.ui.admin.top

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.Member
import com.example.repository.MemberRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class TopViewModel @Inject constructor (
    private val memberRepository: MemberRepository
) : ViewModel() {

    private val _membersList: MutableLiveData<List<Member>> = MutableLiveData()

    val membersList: LiveData<List<Member>> = _membersList

    fun fetch() {
        viewModelScope.launch {
            memberRepository.refresh()
            _membersList.value = memberRepository.findAll()
            Timber.v("result: ${_membersList.value}")
        }
    }
}
