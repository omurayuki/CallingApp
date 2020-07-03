package com.example.api

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.example.model.Member
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseDbClient(
    private val ref: DatabaseReference
) {

    suspend fun fetchMember(): List<Member> = suspendCoroutine { cont ->
        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.getValue<List<Member?>>()?.filterNotNull()?.let {
                    cont.resumeWith(Result.success(it))
                }
            }

            override fun onCancelled(e: DatabaseError) {
                cont.resumeWithException(e.toException())
            }
        })
    }
}