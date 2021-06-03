package com.teamtuna.emotionaldiary.datasource

import com.teamtuna.emotionaldiary.entity.Emotion

interface LocalDataSource {
    suspend fun add(emotion: Emotion, reason : String) : Long
}