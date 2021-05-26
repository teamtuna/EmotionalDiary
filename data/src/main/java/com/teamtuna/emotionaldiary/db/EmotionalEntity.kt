package com.teamtuna.emotionaldiary.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EmotionalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val value: String = ""
)
