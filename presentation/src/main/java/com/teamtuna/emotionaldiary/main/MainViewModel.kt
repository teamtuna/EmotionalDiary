package com.teamtuna.emotionaldiary.main

import androidx.lifecycle.ViewModel
import com.teamtuna.emotionaldiary.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(mainUseCase : MainUseCase): ViewModel() {

}