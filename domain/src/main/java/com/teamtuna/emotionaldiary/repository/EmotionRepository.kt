package com.teamtuna.emotionaldiary.repository

import com.teamtuna.emotionaldiary.entity.DailyEmotion
import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.entity.Result
import java.util.Date

interface EmotionRepository {
    suspend fun add(emotion: Emotion, reason: String): Result<Long>
    suspend fun replace(dailyEmotion: DailyEmotion): Result<Boolean>
    suspend fun add(emotion: Emotion, date: Date, reason: String): Result<Long>
    suspend fun get(id: Long): Result<DailyEmotion>
}
