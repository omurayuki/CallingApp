package com.example.api.di

import com.example.api.FirebaseDbClient
import com.example.api.SlackService
import com.google.firebase.database.DatabaseReference
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create

@Module
internal object DataApiModule {

    @Provides fun provideSlackService(
        @DataApiConfig.SlackApiUrl url: String,
        @DataApiConfig.SlackToken token: String
    ): SlackService {
        val contentType: MediaType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(url)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                    .addNetworkInterceptor { chain ->
                        // Headerにトークンをセットする
                        val request = chain.request()
                        val headers =
                            request.headers.newBuilder().set("Authorization", "Bearer $token")
                                .build()
                        chain.proceed(request.newBuilder().headers(headers).build())
                    }
                    .build()
            )
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
            .create()
    }

    @Provides fun provideFirebaseDbClient(ref: DatabaseReference): FirebaseDbClient {
        return FirebaseDbClient(ref)
    }
}