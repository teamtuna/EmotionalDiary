package com.teamtuna.emotionaldiary.entity

import java.time.LocalDateTime

data class DailyEmotion(
    val id: UniqId,
    val emotion: Emotion,
    val date: LocalDateTime,
    val photo: String?,
    val location: String?,
    val reason: String
) {
    companion object {
        val EMPTY = DailyEmotion(
            id = 0L,
            emotion = Emotion.FEAR,
            date = LocalDateTime.MIN,
            photo = null,
            location = null,
            reason = ""
        )
    }
}

typealias UniqId = Long
