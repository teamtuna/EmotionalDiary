package com.teamtuna.emotionaldiary.domain.usecase

import com.teamtuna.emotionaldiary.domain.entity.Emotion
import com.teamtuna.emotionaldiary.domain.entity.Result
import com.teamtuna.emotionaldiary.domain.repository.EmotionRepository
import java.time.LocalDateTime
import javax.inject.Inject

class EmotionAddByDateUseCase @Inject constructor(
    private val emotionRepository: EmotionRepository
) {
    suspend operator fun invoke(
        emotion: Emotion,
        date: LocalDateTime,
        reason: String
    ): Result<Long> {
        return emotionRepository.add(emotion, date, reason)
    }
}
