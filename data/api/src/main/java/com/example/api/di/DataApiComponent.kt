package com.example.api.di

import com.example.api.FirebaseDbClient
import com.example.api.SlackService
import com.google.firebase.database.DatabaseReference
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataApiModule::class
    ]
)

interface DataApiComponent {

    val slackService: SlackService

    val firebaseDbClient: FirebaseDbClient

    @Component.Factory
    interface  Factory {
        fun create(
            @BindsInstance @DataApiConfig.SlackApiUrl url: String,
            @BindsInstance @DataApiConfig.SlackToken token: String,
            @BindsInstance databaseReference: DatabaseReference
        ): DataApiComponent
    }

    companion object {
        fun factory(): Factory {
            return DaggerDataApiComponent.factory()
        }
    }
}