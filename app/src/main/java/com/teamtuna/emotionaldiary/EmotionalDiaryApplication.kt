package com.teamtuna.emotionaldiary

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EmotionalDiaryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}