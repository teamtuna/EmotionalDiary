package com.teamtuna.emotionaldiary.usecase

import com.teamtuna.emotionaldiary.entity.Emotional
import com.teamtuna.emotionaldiary.repository.EmotionalRepository

class EmotionalAddUseCase(private val emotionalRepository: EmotionalRepository) {
    operator fun invoke(emotional: Emotional, reason: String): Int {
        return emotionalRepository.add(emotional, reason)
    }
}