package com.teamtuna.emotionaldiary.repository

import com.teamtuna.emotionaldiary.datasource.LocalDataSource
import com.teamtuna.emotionaldiary.entity.DailyEmotion
import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.entity.ErrorModel
import com.teamtuna.emotionaldiary.entity.Result
import com.teamtuna.emotionaldiary.entity.UniqId
import com.teamtuna.emotionaldiary.map
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

    override suspend fun get(id: UniqId): Result<DailyEmotion> {
        return localDataSource.get(id)?.let {
            Result.Success(it.map())
        } ?: Result.Fail(ErrorModel())
    }
}
