package com.teamtuna.emotionaldiary.entity

data class DailyEmotion(val id: UniqId, val emotion: Emotion, val reason: String)

typealias UniqId = Long