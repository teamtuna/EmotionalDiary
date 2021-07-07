package com.teamtuna.emotionaldiary.repository

import com.teamtuna.emotionaldiary.entity.DailyEmotion
import com.teamtuna.emotionaldiary.entity.Emotion

interface EmotionRepository {
    suspend fun add(emotion: Emotion, reason: String): Long
    suspend fun get(id: Long): DailyEmotion
}
