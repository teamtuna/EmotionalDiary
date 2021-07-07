package com.teamtuna.emotionaldiary.repository

import com.teamtuna.emotionaldiary.datasource.LocalDataSource
import com.teamtuna.emotionaldiary.entity.DailyEmotion
import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.map

class EmotionRepositoryImpl(private val localDataSource: LocalDataSource) : EmotionRepository {
    override suspend fun add(emotion: Emotion, reason: String): Long {
        return localDataSource.add(emotion, reason)
    }

    override suspend fun get(id: Long): DailyEmotion {
        return requireNotNull(localDataSource.get(id)).map()
    }
}