package com.teamtuna.emotionaldiary.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teamtuna.emotionaldiary.entity.DailyEmotion
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor() : ViewModel() {

    private val _emotionList = MutableLiveData<List<DailyEmotion>>()
    val emotionList: LiveData<List<DailyEmotion>> get() = _emotionList

}