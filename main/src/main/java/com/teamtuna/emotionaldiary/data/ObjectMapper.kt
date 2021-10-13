package com.teamtuna.emotionaldiary.data

import com.teamtuna.emotionaldiary.data.db.EmotionalEntity
import com.teamtuna.emotionaldiary.domain.entity.DailyEmotion

fun EmotionalEntity.toDailyEmotion() = DailyEmotion(
    id = id,
    emotion = emotion,
    date = date,
    location = location,
    photo = photo,
    reason = reason
)

fun DailyEmotion.toEntity() = EmotionalEntity(
    id = id,
    emotion = emotion,
    date = date,
    location = location,
    photo = photo,
    reason = reason
)
