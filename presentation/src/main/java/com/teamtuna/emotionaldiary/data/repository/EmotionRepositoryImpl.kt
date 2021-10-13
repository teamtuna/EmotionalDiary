package com.teamtuna.emotionaldiary.data.repository

import com.teamtuna.emotionaldiary.data.datasource.LocalDataSource
import com.teamtuna.emotionaldiary.data.toDailyEmotion
import com.teamtuna.emotionaldiary.domain.entity.DailyEmotion
import com.teamtuna.emotionaldiary.domain.entity.Emotion
import com.teamtuna.emotionaldiary.domain.entity.Result
import com.teamtuna.emotionaldiary.domain.repository.EmotionRepository
import java.time.LocalDateTime
import javax.inject.Inject

class EmotionRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : EmotionRepository {
    override suspend fun add(
        emotion: Emotion,
        reason: String
    ): Result<Long> {
        return Result.Success(localDataSource.add(emotion, reason))
    }

    override suspend fun replace(dailyEmotion: DailyEmotion): Result<Boolean> {
        return Result.Success(localDataSource.replace(dailyEmotion))
    }

    override suspend fun add(emotion: Emotion, date: LocalDateTime, reason: String): Result<Long> {
        return Result.Success(localDataSource.add(emotion, date, reason))
    }

    override suspend fun get(id: com.teamtuna.emotionaldiary.domain.entity.UniqId): Result<DailyEmotion> {
        return localDataSource.get(id)?.let {
            Result.Success(it.toDailyEmotion())
        } ?: Result.Fail(com.teamtuna.emotionaldiary.domain.entity.ErrorModel())
    }

    override suspend fun delete(id: Long) {
        localDataSource.delete(id)
    }

    override suspend fun add(dailyEmotion: DailyEmotion): Result<Long> {
        return Result.Success(localDataSource.add(dailyEmotion))
    }
}
