package com.teamtuna.emotionaldiary.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.entity.process
import com.teamtuna.emotionaldiary.usecase.EmotionAddByDateUseCase
import com.teamtuna.emotionaldiary.usecase.EmotionAddUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class EmotionAddViewModel @Inject constructor(
    private val emotionAddUseCase: EmotionAddUseCase,
    private val emotionAddByDateUseCase: EmotionAddByDateUseCase
) : ViewModel() {

    private val _response = MutableLiveData<Long>()
    val response: LiveData<Long>
        get() = _response

    fun add(emotion: Emotion, reason: String) {
        viewModelScope.launch(Dispatchers.IO) {
            emotionAddUseCase(emotion, reason).process({
                // Cannot invoke setValue on a background thread
                _response.postValue(it)
            }, {
            })
        }
    }

    fun add(emotion: Emotion, date: Date, reason: String) {
        viewModelScope.launch(Dispatchers.IO) {
            emotionAddByDateUseCase(emotion, date, reason).process({
                // Cannot invoke setValue on a background thread
                _response.postValue(it)
            }, {
            })
        }
    }
}
