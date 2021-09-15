package com.teamtuna.emotionaldiary.write

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamtuna.emotionaldiary.KEY_EMOTION
import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.usecase.EmotionAddUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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
