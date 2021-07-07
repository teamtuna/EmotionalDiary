package com.teamtuna.emotionaldiary.datasource

import com.teamtuna.emotionaldiary.db.EmotionalDao
import com.teamtuna.emotionaldiary.db.EmotionalEntity
import com.teamtuna.emotionaldiary.entity.Emotion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSourceImpl(
    private val emotionDao: EmotionalDao
) : LocalDataSource {
    override suspend fun add(emotion: Emotion, reason: String): Long = withContext(Dispatchers.IO) {
        val entity = EmotionalEntity(emotion = emotion, reason = reason)
        return@withContext emotionDao.insertEmotional(entity)
    }

    override suspend fun get(id: Long): EmotionalEntity? = withContext(Dispatchers.IO) {
        return@withContext emotionDao.getEmotional(id)
    }
}