package com.example.db.di

import android.content.Context
import com.example.db.MemberDao
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DbModule::class
    ]
)
interface DbComponent {

    val employeeDao: MemberDao

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): DbComponent
    }

    companion object {
        fun factory(): Factory {
            return DaggerDbComponent.factory()
        }
    }
}
