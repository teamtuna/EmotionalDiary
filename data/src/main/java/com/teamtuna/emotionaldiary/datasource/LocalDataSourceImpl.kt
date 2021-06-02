package com.teamtuna.emotionaldiary.datasource

import com.teamtuna.emotionaldiary.db.EmotionalDao
import com.teamtuna.emotionaldiary.db.EmotionalEntity
import com.teamtuna.emotionaldiary.entity.Emotion

class LocalDataSourceImpl(
    private val emotionDao: EmotionalDao
) : LocalDataSource {

    override suspend fun add(emotion: Emotion, reason: String): Long {
        val entity: EmotionalEntity = EmotionalEntity(emotion = emotion, reason = reason)
        return emotionDao.insertEmotional(entity)
    }
}