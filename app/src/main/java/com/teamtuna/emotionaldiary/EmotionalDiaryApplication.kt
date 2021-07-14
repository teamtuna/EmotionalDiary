package com.teamtuna.emotionaldiary

import android.app.Application
import com.teamtuna.emotionaldiary.analytics.firebaseAnalyticsTrackScreenViews
import com.teamtuna.emotionaldiary.fcm.EmotionalDiaryFirebaseMessagingService
import com.teamtuna.emotionaldiary.fcm.EmotionalDiaryNotificationChannelManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EmotionalDiaryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        EmotionalDiaryNotificationChannelManager.createNotificationChannel(applicationContext)
        EmotionalDiaryFirebaseMessagingService.getToken()

        uncaughtExceptionLoggingHandler()

        firebaseAnalyticsTrackScreenViews()

        easterEgg()
    }
}
