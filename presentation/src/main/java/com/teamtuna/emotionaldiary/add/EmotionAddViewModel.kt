package com.teamtuna.emotionaldiary.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.usecase.EmotionAddUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmotionAddViewModel @Inject constructor(
        private val emotionAddUseCase: EmotionAddUseCase
): ViewModel() {

    private val _response = MutableLiveData<Long>()
    val response : LiveData<Long>
        get() = _response

    fun add(emotion: Emotion, reason: String) {
        viewModelScope.launch {
            emotionAddUseCase(emotion, reason).let {
                _response.value = it
            }
        }
    }

}