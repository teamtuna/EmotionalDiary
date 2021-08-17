package com.teamtuna.emotionaldiary.usecase

import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.entity.Result
import com.teamtuna.emotionaldiary.repository.EmotionRepository
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
