package com.teamtuna.emotionaldiary.repository

import com.teamtuna.emotionaldiary.entity.DiaryEntity
import com.teamtuna.emotionaldiary.entity.Emotional

interface EmotionalRepository {
    fun add(emotional: Emotional, reason: String): Int
}
