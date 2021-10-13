package com.teamtuna.emotionaldiary.presentation.timeline

import android.net.Uri
import com.teamtuna.emotionaldiary.domain.entity.Emotion

data class EmotionItem(
    val emotion: Emotion,
    val diaryContent: String,
    val imageUrl: Uri
)
