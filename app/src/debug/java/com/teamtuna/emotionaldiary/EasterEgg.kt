package com.teamtuna.emotionaldiary

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.log.Log
import androidx.appcompat.app.AlertDialog
import com.google.firebase.crashlytics.FirebaseCrashlytics

@Suppress("unused")
class EasterEgg(private val activity: Activity) {
    fun crashFunction() {
        val e = RuntimeException("Test Crash") // Force a crash
        FirebaseCrashlytics.getInstance().recordException(e)
        throw e
    }

    fun allActivity() {
        val list = activity.packageManager.getPackageInfo(
            activity.packageName,
            PackageManager.GET_ACTIVITIES
        ).activities
        val items = list
            .filter { it.name.startsWith(activity.packageName) }
            .filterNot { it.name == javaClass.name }
            .map { it.name }
            .toTypedArray()

        AlertDialog.Builder(activity)
            .setItems(items) { dialog, which ->
                try {
                    val item = (dialog as AlertDialog).listView.getItemAtPosition(which) as String
                    activity.startActivity(Intent().setClassName(activity, item))
                } catch (e: Exception) {
                    Log.printStackTrace(e)
                }
            }
            .show()
    }
}
