package com.teamtuna.emotionaldiary.repository

import com.teamtuna.emotionaldiary.datasource.LocalDataSource
import com.teamtuna.emotionaldiary.entity.Emotion

class EmotionRepositoryImpl(private val localDataSource: LocalDataSource) : EmotionRepository {
    override fun add(emotion: Emotion, reason: String): Int {
        return localDataSource.add(emotion, reason)
    }
}