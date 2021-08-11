package com.teamtuna.emotionaldiary.entity

import java.util.Date

data class DailyEmotion(val id: UniqId, val emotion: Emotion, val date: Date, val reason: String)

typealias UniqId = Long
