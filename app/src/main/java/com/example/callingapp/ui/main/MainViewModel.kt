package com.example.callingapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repository.MemberRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val memberRepository: MemberRepository
): ViewModel() {

    fun refresh() {
        viewModelScope.launch {
            Timber.d("refresh start.")
            memberRepository.refresh()
            Timber.d("refresh complete.")
        }

        viewModelScope.launch {
            Timber.d("list start")
            val list = memberRepository.findAll()
            Timber.d("list complete. ${list.size} people, like ${list.getOrNull(0)}")
        }

        viewModelScope.launch {
            memberRepository.searchResult().observeForever {
                Timber.d("search recieve. size: ${it.size}, like ${it.getOrNull(0)}")
            }
            Timber.d("search start.")
            val job1 = async { memberRepository.searchWord("2") }
            delay(1100) // searchWordの実行時間より長く待つとdelayの間に完了する
            val job2 = async { memberRepository.searchWord("3") }
//            delay(800) // searchWordの実行より短い時間しか待たないと次のsearchWordが実行されてjob2はキャンセルされる
            val job3 = async { memberRepository.searchWord("イイノ") }
        }
    }
}