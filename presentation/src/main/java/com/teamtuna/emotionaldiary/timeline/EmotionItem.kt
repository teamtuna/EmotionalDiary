package com.teamtuna.emotionaldiary.timeline

import android.net.Uri
import com.teamtuna.emotionaldiary.entity.Emotion

data class EmotionItem(
    val emotion: Emotion,
    val diaryContent: String,
    val imageUrl: Uri
)
