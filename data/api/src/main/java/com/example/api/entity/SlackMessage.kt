package com.example.api.entity

import kotlinx.serialization.Serializable


@Serializable
data class SlackMessage(
    val channel: String,
    val text: String,
    val link_names: Boolean = true
)