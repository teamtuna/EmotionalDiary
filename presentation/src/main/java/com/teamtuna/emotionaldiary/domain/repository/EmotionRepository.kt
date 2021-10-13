package com.teamtuna.emotionaldiary.domain.repository

import com.teamtuna.emotionaldiary.domain.entity.DailyEmotion
import com.teamtuna.emotionaldiary.domain.entity.Emotion
import com.teamtuna.emotionaldiary.domain.entity.Result
import java.time.LocalDateTime

interface EmotionRepository {
    suspend fun add(emotion: Emotion, reason: String): Result<Long>
    suspend fun replace(dailyEmotion: DailyEmotion): Result<Boolean>
    suspend fun add(emotion: Emotion, date: LocalDateTime, reason: String): Result<Long>
    suspend fun get(id: Long): Result<DailyEmotion>
    suspend fun delete(id: Long)
    suspend fun add(vararg dailyEmotion: DailyEmotion): Result<LongArray>
}
