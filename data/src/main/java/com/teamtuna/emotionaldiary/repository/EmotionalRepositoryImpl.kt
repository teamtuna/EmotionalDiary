package com.teamtuna.emotionaldiary.repository

import com.teamtuna.emotionaldiary.datasource.LocalDataSource
import com.teamtuna.emotionaldiary.entity.Emotional

class EmotionalRepositoryImpl(private val localDataSource: LocalDataSource) : EmotionalRepository {
    override fun add(emotional: Emotional, reason: String): Int {
        return localDataSource.add(emotional, reason)
    }
}