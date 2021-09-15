package com.teamtuna.emotionaldiary

import com.teamtuna.emotionaldiary.db.EmotionalEntity
import com.teamtuna.emotionaldiary.entity.DailyEmotion

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
