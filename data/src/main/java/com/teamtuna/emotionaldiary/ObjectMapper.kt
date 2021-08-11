package com.teamtuna.emotionaldiary

import com.teamtuna.emotionaldiary.db.EmotionalEntity
import com.teamtuna.emotionaldiary.entity.DailyEmotion

fun EmotionalEntity.toDailyEmotion() = DailyEmotion(id, emotion, reason)

fun DailyEmotion.toEntity() = EmotionalEntity(id, emotion, reason)
