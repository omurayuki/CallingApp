package com.example.api

import com.example.api.entity.SlackMessage
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SlackService {

    @POST("chat.postMessage")
    @Headers("Content-type: application/json", "Authorization: Bearer xxxxx")
    fun postMessage(@Body message: SlackMessage): Call<Unit>
}