package com.teamtuna.emotionaldiary.repository

import com.teamtuna.emotionaldiary.datasource.LocalDataSource
import com.teamtuna.emotionaldiary.entity.Emotion
import javax.inject.Inject

class EmotionRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) : EmotionRepository {
    override fun add(emotion: Emotion, reason: String): Int {
        return localDataSource.add(emotion, reason)
    }
}