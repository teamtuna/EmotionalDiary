package com.teamtuna.emotionaldiary.usecase

import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.entity.Result
import com.teamtuna.emotionaldiary.repository.EmotionRepository

class EmotionAddUseCase(private val emotionRepository: EmotionRepository) {
    suspend operator fun invoke(emotion: Emotion, reason: String): Result<Long> {
        return emotionRepository.add(emotion, reason)
    }
}
