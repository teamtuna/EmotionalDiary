package com.teamtuna.emotionaldiary.repository

import com.teamtuna.emotionaldiary.entity.Emotion

interface EmotionRepository {
    fun add(emotion: Emotion, reason: String): Int
}
