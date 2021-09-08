package com.teamtuna.emotionaldiary.entity

import java.time.LocalDateTime

data class DailyEmotion(
    val id: UniqId,
    val emotion: Emotion,
    val date: LocalDateTime,
    val imageUrl: String? = null,
    val reason: String
)

typealias UniqId = Long
