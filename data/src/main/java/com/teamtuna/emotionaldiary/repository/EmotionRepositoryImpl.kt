package com.teamtuna.emotionaldiary.repository

import com.teamtuna.emotionaldiary.datasource.LocalDataSource
import com.teamtuna.emotionaldiary.entity.*
import com.teamtuna.emotionaldiary.map
import javax.inject.Inject

class EmotionRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) : EmotionRepository {
    override suspend fun add(
        emotion: Emotion,
        reason: String
    ): Result<Long> {
        return Result.Success(localDataSource.add(emotion, reason))
    }

    override suspend fun get(id: UniqId): Result<DailyEmotion> {
        return localDataSource.get(id)?.let {
            Result.Success(it.map())
        } ?: Result.Fail(ErrorModel())

    }
}