package com.teamtuna.emotionaldiary.repository

import com.teamtuna.emotionaldiary.entity.Emotion

interface EmotionRepository {
    suspend fun add(emotion: Emotion, reason: String): Long
}
