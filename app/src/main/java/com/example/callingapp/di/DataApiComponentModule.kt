package com.example.callingapp.di

import com.example.api.FirebaseDbClient
import com.example.api.SlackService
import com.example.api.di.DataApiComponent
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides

@Module
object DataApiComponentModule {

    @Provides fun provideApiComponent(
        @SlackApiUrl url: String,
        @SlackToken token: String,
        database: FirebaseDatabase
    ): DataApiComponent {
        return DataApiComponent.factory().create(url, token, database.reference)
    }

    @Provides fun provideSlackService(component: DataApiComponent): SlackService {
        return component.slackService
    }

    @Provides fun provideFirebaseDbClient(component: DataApiComponent): FirebaseDbClient {
        return component.firebaseDbClient
    }
}