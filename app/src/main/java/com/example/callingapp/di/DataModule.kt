package com.example.callingapp.di

import android.content.Context
import android.content.SharedPreferences
import com.example.callingapp.App
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides

@Module
object DataModule {

    @Provides
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    fun provideSharedPreferences(app: App): SharedPreferences {
        return app.getSharedPreferences("app", Context.MODE_PRIVATE)
    }
}