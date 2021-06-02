package com.teamtuna.emotionaldiary.usecase

import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.repository.EmotionRepository
import javax.inject.Inject

class EmotionAddUseCase @Inject constructor(private val emotionRepository: EmotionRepository) {
    operator fun invoke(emotion: Emotion, reason: String): Int {
        return emotionRepository.add(emotion, reason)
    }
}