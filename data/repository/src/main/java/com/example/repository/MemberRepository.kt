package com.example.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.api.FirebaseDbClient
import com.example.db.MemberDao
import com.example.model.Member
import kotlinx.coroutines.*

class MemberRepository(
    private val api: FirebaseDbClient,
    private val dao: MemberDao
) {

    private var searchJob: Job? = null

    private val _searchResult: MutableLiveData<List<Member>> = MutableLiveData()

    suspend fun refresh() = withContext(Dispatchers.IO) {

        val list = api.fetchMember()
        dao.invalidateWith(list)
    }

    suspend fun findAll(): List<Member> {
        return dao.findAll()
    }

    suspend fun searchWord(query: String) = coroutineScope {
        searchJob?.cancel()
        searchJob = async {
            val findByKana = dao.findByKana(query)

            if (isActive) {
                _searchResult.postValue(findByKana)
            } else {
                println("searchWord キャンセルもしくは完了しました")
            }
        }.apply {
            invokeOnCompletion {
                println("searchWord canceled: $it")
            }
        }
    }

    fun searchResult(): LiveData<List<Member>> {
        return _searchResult
    }
}