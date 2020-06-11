package com.example.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import java.io.Serializable

@Entity
class Member(
    @PrimaryKey
    val id: Long = 0,
    val name: String = "",
    val kana: String = "",
    val title: String? = "",
    val division: String? = "",
    val ordering: Int? = 0,
    val slackName: String = ""
): Serializable