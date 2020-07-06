package com.example.callingapp.di

import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SlackApiUrl

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SlackToken

@Module
object ConfigModule {

    @Provides
    @SlackApiUrl fun provideSlackApiUrl(): String = "https://slack.com/api/"

    @Provides
    @SlackToken fun provideSlackToken(): String = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
}
