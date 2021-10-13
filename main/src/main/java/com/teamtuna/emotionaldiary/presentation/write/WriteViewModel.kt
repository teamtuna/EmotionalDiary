package com.teamtuna.emotionaldiary.presentation.write

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamtuna.emotionaldiary.domain.entity.Emotion
import com.teamtuna.emotionaldiary.domain.usecase.EmotionAddUseCase
import com.teamtuna.emotionaldiary.presentation.KEY_EMOTION
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WriteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val emotionAddUseCase: EmotionAddUseCase
) : ViewModel() {

    private val initialEmotion =
        savedStateHandle.get<String>(KEY_EMOTION)?.let { Emotion.valueOf(it) }

    // UI state exposed to the UI
    private val _uiState =
        MutableStateFlow(WriteUiState.EMPTY.copy(emotion = initialEmotion ?: Emotion.JOY))
    val uiState: StateFlow<WriteUiState> = _uiState.asStateFlow()

    fun confirm() {
        viewModelScope.launch {
            val currentValue = uiState.value
            val result = emotionAddUseCase(currentValue.emotion, currentValue.content)
        }
    }

    fun onEmotionChanged(emotion: Emotion) {
        _uiState.update {
            it.copy(emotion = emotion)
        }
    }

    fun onContentChanged(content: String) {
        _uiState.update {
            it.copy(content = content)
        }
    }
}
