package com.example.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.model.Member

@Database(
    version = 2,
    entities = [
        Member::class
    ]
)
abstract class CallingDatabase: RoomDatabase() {

    abstract fun memberDao(): MemberDao
}
