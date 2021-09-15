package com.teamtuna.emotionaldiary.write

import com.teamtuna.emotionaldiary.entity.Emotion

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
