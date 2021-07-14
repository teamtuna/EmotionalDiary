package com.teamtuna.emotionaldiary

import com.teamtuna.emotionaldiary.db.EmotionalEntity
import com.teamtuna.emotionaldiary.entity.DailyEmotion

fun EmotionalEntity.map() = DailyEmotion(id, emotion, reason)
