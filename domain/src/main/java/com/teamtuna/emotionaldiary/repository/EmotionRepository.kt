package com.teamtuna.emotionaldiary.repository

import com.teamtuna.emotionaldiary.entity.DailyEmotion
import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.entity.Result
import com.teamtuna.emotionaldiary.entity.UniqId

interface EmotionRepository {
    suspend fun add(emotion: Emotion, reason: String): Result<Long>
    suspend fun replace(id: UniqId, emotion: Emotion, reason: String): Result<Boolean>
    suspend fun get(id: Long): Result<DailyEmotion>
}
