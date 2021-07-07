package com.teamtuna.emotionaldiary.datasource

import com.teamtuna.emotionaldiary.db.EmotionalEntity
import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.entity.UniqId

interface LocalDataSource {
    suspend fun add(emotion: Emotion, reason : String) : UniqId

    suspend fun get(id : UniqId) : EmotionalEntity?
}