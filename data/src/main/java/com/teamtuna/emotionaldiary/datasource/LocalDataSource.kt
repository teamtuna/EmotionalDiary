package com.teamtuna.emotionaldiary.datasource

import com.teamtuna.emotionaldiary.db.EmotionalEntity
import com.teamtuna.emotionaldiary.entity.DailyEmotion
import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.entity.UniqId
import java.time.LocalDateTime

interface LocalDataSource {
    suspend fun add(emotion: Emotion, reason: String): UniqId
    suspend fun add(emotion: Emotion, date: LocalDateTime, reason: String): UniqId
    suspend fun get(id: UniqId): EmotionalEntity?

    suspend fun replace(dailyEmotion: DailyEmotion): Boolean

    suspend fun delete(id: UniqId)
}
