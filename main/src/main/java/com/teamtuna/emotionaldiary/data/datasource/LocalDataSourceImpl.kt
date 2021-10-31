package com.teamtuna.emotionaldiary.data.datasource

import com.teamtuna.emotionaldiary.data.db.EmotionalDao
import com.teamtuna.emotionaldiary.data.db.EmotionalEntity
import com.teamtuna.emotionaldiary.data.toEntity
import com.teamtuna.emotionaldiary.domain.entity.DailyEmotion
import com.teamtuna.emotionaldiary.domain.entity.Emotion
import com.teamtuna.emotionaldiary.domain.entity.UniqId
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

    override suspend fun addAll(vararg dailyEmotion: DailyEmotion): LongArray =
        emotionDao.blobInsert(*dailyEmotion.map { it.toEntity() }.toTypedArray())

    override suspend fun get() = emotionDao.getEmotional()
}
