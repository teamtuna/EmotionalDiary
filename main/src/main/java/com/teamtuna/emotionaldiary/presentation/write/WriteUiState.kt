package com.teamtuna.emotionaldiary.presentation.write

import com.teamtuna.emotionaldiary.domain.entity.Emotion

data class WriteUiState(
    val picture: String?,
    val emotion: Emotion,
    var content: String
) {
    companion object {
        val EMPTY = WriteUiState(
            picture = null,
            emotion = Emotion.JOY,
            content = ""
        )
    }
}
