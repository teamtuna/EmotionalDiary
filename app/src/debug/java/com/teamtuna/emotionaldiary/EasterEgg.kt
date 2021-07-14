package com.teamtuna.emotionaldiary

import android.app.Activity
import android.widget.Toast
import com.google.firebase.crashlytics.FirebaseCrashlytics

@Suppress("unused")
class EasterEgg(private val activity: Activity) {
    fun crashFunction() {
        val e = RuntimeException("Test Crash") // Force a crash
        FirebaseCrashlytics.getInstance().recordException(e)
        throw e
    }

    fun toastHello() {
        Toast.makeText(activity, "Hello", Toast.LENGTH_SHORT).show()
    }
}
