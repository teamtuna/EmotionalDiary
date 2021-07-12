@file:Suppress("unused")

package com.teamtuna.emotionaldiary

import android.app.Activity
import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.core.content.pm.PackageInfoCompat
import androidx.core.view.children
import androidx.core.view.drawToBitmap
import com.teamtuna.emotionaldiary.firebasestorage.firebaseStoragePutFiles
import java.io.File
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*


fun Application.uncaughtExceptionLoggingHandler() = UncaughtExceptionLoggingHandler(applicationContext)

class UncaughtExceptionLoggingHandler(
    appContext: Context,
    actor: ((File) -> Unit)? = ::firebaseStoragePutFiles,
) {
    private var versionTag: String = "version ${appContext.runCatching { PackageInfoCompat.getLongVersionCode(packageManager.getPackageInfo(packageName, 0)) }.getOrDefault(-1L)}"
    private var logDir = File(appContext.externalCacheDir, "temp")


    init {
        lastActivityWeakReference(appContext)
        uncaughtExceptionHandler {
            val uncaughtExceptionFilename = uncaughtExceptionFilename()
            uncaughtScreen(uncaughtExceptionFilename)
            uncaughtStackTraceText(uncaughtExceptionFilename, it)
        }

        actor?.invoke(logDir)
    }

    private fun uncaughtExceptionHandler(block: (stackTraceText: String) -> Unit) {
        val def = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            block(throwable.stackTraceToString())
            def?.uncaughtException(thread, throwable)
        }
    }

    private var mLastActivityWeakReference: WeakReference<Activity>? = null
    private fun lastActivityWeakReference(context: Context) {
        (context.applicationContext as Application).registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {}
            override fun onActivityResumed(activity: Activity) {
                mLastActivityWeakReference = WeakReference(activity)
            }
        })
    }


    private fun uncaughtExceptionFilename(): String {
        val now = SimpleDateFormat("yyyyMMdd-HHmmss", Locale.getDefault()).format(Date(System.currentTimeMillis()))
        return now + mLastActivityWeakReference?.get()?.run {
            var name = ":" + javaClass.simpleName

            (window.decorView as ViewGroup).findViewByChild(WebView::class.java)?.let {
                name += ":" + it.url?.substringAfterLast("/")?.substringBeforeLast(".")
                name += ":" + it.title
            }
            name
        }
    }

    private fun uncaughtStackTraceText(filename: String, stackTraceText: String) {
        File(logDir, "$filename.txt")
            .apply { parentFile?.mkdirs() }
            .writeText("$versionTag\n$filename\n$stackTraceText")
    }

    private fun uncaughtScreen(filename: String) {
        mLastActivityWeakReference?.get()?.window?.decorView?.drawToBitmap(Bitmap.Config.RGB_565)
            ?.let { bitmap ->
                File(logDir, "/$filename.jpeg")
                    .apply { parentFile?.mkdirs() }
                    .outputStream().use {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, it)
                    }
            }
    }


    ////////////////////////////////////////////////////////////////////////////////
    @Suppress("UNCHECKED_CAST")
    private fun <T : View> ViewGroup.findViewByChild(clz: Class<T>): T? {
        val result = children.firstOrNull { clz.isInstance(it) }
            ?: children.filter { it is ViewGroup }
                .map { (it as ViewGroup).findViewByChild(clz) }
                .firstOrNull { clz.isInstance(it) }
        return result as? T
    }
}


