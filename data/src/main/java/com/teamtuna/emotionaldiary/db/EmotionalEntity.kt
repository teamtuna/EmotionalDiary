package com.teamtuna.emotionaldiary.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.entity.UniqId

@Entity
data class EmotionalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: UniqId = 0,
    val emotion: Emotion,
    val reason: String = ""
)
