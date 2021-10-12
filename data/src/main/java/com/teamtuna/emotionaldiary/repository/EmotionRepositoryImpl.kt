package com.teamtuna.emotionaldiary.repository

import androidx.paging.PagingSource
import com.teamtuna.emotionaldiary.datasource.LocalDataSource
import com.teamtuna.emotionaldiary.db.EmotionalEntity
import com.teamtuna.emotionaldiary.entity.DailyEmotion
import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.entity.ErrorModel
import com.teamtuna.emotionaldiary.entity.Result
import com.teamtuna.emotionaldiary.entity.UniqId
import com.teamtuna.emotionaldiary.toDailyEmotion
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

    override suspend fun get(id: UniqId): Result<DailyEmotion> {
        return localDataSource.get(id)?.let {
            Result.Success(it.toDailyEmotion())
        } ?: Result.Fail(ErrorModel())
    }

    override suspend fun delete(id: Long) {
        localDataSource.delete(id)
    }

    override suspend fun add(dailyEmotion: DailyEmotion): Result<Long> {
        return Result.Success(localDataSource.add(dailyEmotion))
    }
}
