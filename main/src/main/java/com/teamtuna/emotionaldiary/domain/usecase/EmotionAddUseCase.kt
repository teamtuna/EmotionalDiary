package com.teamtuna.emotionaldiary.domain.usecase

import com.teamtuna.emotionaldiary.domain.entity.Emotion
import com.teamtuna.emotionaldiary.domain.entity.Result
import com.teamtuna.emotionaldiary.domain.repository.EmotionRepository
import javax.inject.Inject

class EmotionAddUseCase @Inject constructor(private val emotionRepository: EmotionRepository) {
    suspend operator fun invoke(emotion: Emotion, reason: String): Result<Long> {
        return emotionRepository.add(emotion, reason)
    }
}
