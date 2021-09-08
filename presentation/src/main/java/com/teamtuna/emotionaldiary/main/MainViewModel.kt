package com.teamtuna.emotionaldiary.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.teamtuna.emotionaldiary.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainUseCase: MainUseCase) : ViewModel() {
    fun test() = mainUseCase()

    private val _selectedMenu = mutableStateOf<BottomMenu>(BottomMenu.CALENDAR)
    val selectedMenu get() = _selectedMenu

    fun onSelectBottomMenu(menu: BottomMenu) {
        _selectedMenu.value = menu
    }
}

enum class BottomMenu {
    CALENDAR, TIMELINE, ANALYSIS, SETTING
}
