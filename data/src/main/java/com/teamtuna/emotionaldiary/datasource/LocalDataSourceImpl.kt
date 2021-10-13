package com.teamtuna.emotionaldiary.datasource

import com.teamtuna.emotionaldiary.db.EmotionalDao
import com.teamtuna.emotionaldiary.db.EmotionalEntity
import com.teamtuna.emotionaldiary.entity.DailyEmotion
import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.entity.UniqId
import com.teamtuna.emotionaldiary.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class LocalDataSourceImpl(
    private val emotionDao: EmotionalDao
) : LocalDataSource {
    override suspend fun add(emotion: Emotion, reason: String): UniqId =
        withContext(Dispatchers.IO) {
            val entity =
                EmotionalEntity(emotion = emotion, reason = reason, location = null, photo = null)
            return@withContext emotionDao.insertEmotional(entity)
        }

    override suspend fun add(emotion: Emotion, date: LocalDateTime, reason: String): UniqId =
        withContext(Dispatchers.IO) {
            val entity = EmotionalEntity(
                emotion = emotion,
                date = date,
                reason = reason,
                location = null,
                photo = null
            )
            return@withContext emotionDao.insertEmotional(entity)
        }

    override suspend fun get(id: UniqId): EmotionalEntity? = withContext(Dispatchers.IO) {
        return@withContext emotionDao.getEmotional(id)
    }

    override suspend fun replace(dailyEmotion: DailyEmotion): Boolean =
        withContext(Dispatchers.IO) {
            val replacedId = emotionDao.insertEmotional(dailyEmotion.toEntity())
            return@withContext replacedId == dailyEmotion.id
        }

    override suspend fun delete(id: UniqId) {
        withContext(Dispatchers.IO) {
            emotionDao.getEmotional(id)?.run {
                emotionDao.deleteEmotional(this)
            }
        }
    }

    override suspend fun add(dailyEmotion: DailyEmotion): UniqId {
        require(dailyEmotion.id <= 0L)
        return emotionDao.insertEmotional(dailyEmotion.toEntity())
    }

    override suspend fun get() = emotionDao.getEmotional()
}
