package com.example.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.model.Member

@Dao
interface MemberDao {

    @Transaction
    fun invalidateWith(member: List<Member>): List<Long> {
        // idがあるのでもう少し効率的に,削除分だけ消してupcertにしたほうが良い?
        deleteAll()
        return insertAll(member)
    }

    // 返り値はrowIDのリスト
    @Insert
    fun insertAll(schedule: List<Member>): List<Long>

    @Query("select * from member")
    suspend fun findAll(): List<Member>

    @Query("select * from member where id = :id")
    suspend fun findById(id: Long): Member

    @Query("select * from member where kana like '%' || :query || '%'")
    suspend fun findByKana(query: String): List<Member>

    @Query("delete from member")
    fun deleteAll()
}