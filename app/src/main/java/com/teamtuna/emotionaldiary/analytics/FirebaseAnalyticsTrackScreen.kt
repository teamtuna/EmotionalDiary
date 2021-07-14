package com.teamtuna.emotionaldiary.analytics

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.core.os.bundleOf
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.teamtuna.emotionaldiary.authentication.signInAnonymously

fun Application.firebaseAnalyticsTrackScreenViews() {
    val firebaseAnalytics = Firebase.analytics

    signInAnonymously { firebaseAnalytics.setUserId(it.user?.uid) }

    registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
        override fun onActivityStarted(activity: Activity) {}
        override fun onActivityPaused(activity: Activity) {}
        override fun onActivityStopped(activity: Activity) {}
        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
        override fun onActivityDestroyed(activity: Activity) {}
        override fun onActivityResumed(activity: Activity) {
            firebaseAnalytics.logEvent(
                FirebaseAnalytics.Event.SCREEN_VIEW,
                bundleOf(
                    FirebaseAnalytics.Param.SCREEN_NAME to activity.title,
                    FirebaseAnalytics.Param.SCREEN_CLASS to activity.javaClass.simpleName
                )
            )
        }
    })
}
