package com.example.callingapp.di

import com.example.callingapp.App
import com.example.db.MemberDao
import com.example.db.di.DbComponent
import dagger.Module
import dagger.Provides

@Module
object DbComponentModule {

    @Provides
    fun provideDbComponent(app: App): DbComponent {
        return DbComponent.factory().create(app)
    }

    @Provides
    fun provideEmployeeDao(dbComponent: DbComponent): MemberDao {
        return dbComponent.memberDao
    }
}
