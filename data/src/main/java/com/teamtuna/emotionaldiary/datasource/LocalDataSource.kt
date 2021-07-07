package com.teamtuna.emotionaldiary.datasource

import com.teamtuna.emotionaldiary.db.EmotionalEntity
import com.teamtuna.emotionaldiary.entity.Emotion

interface LocalDataSource {
    suspend fun add(emotion: Emotion, reason : String) : Long

    suspend fun get(id : Long) : EmotionalEntity?
}