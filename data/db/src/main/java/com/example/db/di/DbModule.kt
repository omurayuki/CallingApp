package com.example.db.di

import android.content.Context
import androidx.room.Room
import com.example.db.CallingDatabase
import com.example.db.MemberDao
import dagger.Module
import dagger.Provides

@Module
internal object DbModule {

    @Provides
    fun provideCallingDatabase(context: Context): CallingDatabase {
        return Room.databaseBuilder(context, CallingDatabase::class.java, "calling")
            // It's totally cache and less important data so we don't care migrations :)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMemberDao(db: CallingDatabase): MemberDao {
        return db.memberDao()
    }
}
