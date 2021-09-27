package com.teamtuna.emotionaldiary.util

import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.presentation.R

val Emotion?.icon: Int?
    get() = when (this) {
        Emotion.JOY -> R.drawable.input_joy
        Emotion.ANGER -> R.drawable.input_anger
        Emotion.DISGUST -> R.drawable.input_disgust
        Emotion.FEAR -> R.drawable.input_fear
        Emotion.SADNESS -> R.drawable.input_sad
        else -> null
    }


val Emotion?.bg: Int
    get() = when (this) {
        Emotion.JOY -> R.color.emotional_joy
        Emotion.ANGER -> R.color.emotional_anger
        Emotion.DISGUST -> R.color.emotional_disgust
        Emotion.FEAR -> R.color.emotional_fear
        Emotion.SADNESS -> R.color.emotional_sad
        else -> R.color.white
    }

val Emotion?.bg_accent: Int
    get() = when (this) {
        Emotion.JOY -> R.color.emotional_joy_accent
        Emotion.ANGER -> R.color.emotional_anger_accent
        Emotion.DISGUST -> R.color.emotional_disgust_accent
        Emotion.FEAR -> R.color.emotional_fear_accent
        Emotion.SADNESS -> R.color.emotional_sad_accent
        else -> R.color.white
    }
