package com.example.api.di

import javax.inject.Qualifier

annotation class DataApiConfig {
    @Qualifier @Retention(AnnotationRetention.RUNTIME) annotation class SlackApiUrl
    @Qualifier @Retention(AnnotationRetention.RUNTIME) annotation class SlackToken
}