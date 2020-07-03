package com.example.repository

import com.example.api.SlackService
import com.example.api.entity.SlackMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SlackRepository(
    private val client: SlackService
) {

    suspend fun post(channel: String, message: String) {
        withContext(Dispatchers.IO) {
            client.postMessage(
                SlackMessage(channel, message)
            ).execute()
        }
    }
}