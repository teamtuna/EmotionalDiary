package com.teamtuna.emotionaldiary.crashlytics

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.ktx.setCustomKeys
import com.teamtuna.emotionaldiary.authentication.signInAnonymously

fun Application.firebaseCrashlyticsScreenLog() {
    FirebaseCrashlytics.getInstance().log("STARTED")
    signInAnonymously { FirebaseCrashlytics.getInstance().setUserId(it.user?.uid.toString()) }

    registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
        override fun onActivityStarted(activity: Activity) {}
        override fun onActivityPaused(activity: Activity) {}
        override fun onActivityStopped(activity: Activity) {}
        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
        override fun onActivityDestroyed(activity: Activity) {}
        override fun onActivityResumed(activity: Activity) {
            FirebaseCrashlytics.getInstance().setCustomKeys {
                key("str_key", "hello")
                key("bool_key", true)
                key("int_key", 1)
                key("long_key", 1L)
                key("float_key", 1.0f)
                key("double_key", 1.0)
                key(FirebaseAnalytics.Param.SCREEN_NAME, activity.title.toString())
                key(FirebaseAnalytics.Param.SCREEN_CLASS, activity.javaClass.simpleName)
            }

        }
    })
}