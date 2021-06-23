package com.teamtuna.emotionaldiary.repository

import com.teamtuna.emotionaldiary.datasource.LocalDataSource
import com.teamtuna.emotionaldiary.entity.Emotion
import javax.inject.Inject

class EmotionRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) : EmotionRepository {
    override suspend fun add(emotion: Emotion, reason: String): Long {
        return localDataSource.add(emotion, reason)
    }
}