package com.teamtuna.emotionaldiary.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.usecase.EmotionAddUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmotionAddViewModel @Inject constructor(
        private val emotionAddUseCase: EmotionAddUseCase
): ViewModel() {

    private val _response = MutableLiveData<Int>()
    val response : LiveData<Int>
        get() = _response

    fun add(emotion: Emotion, reason: String) {
        _response.value = emotionAddUseCase(emotion, reason)
    }

}