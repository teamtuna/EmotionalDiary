package com.teamtuna.emotionaldiary

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.core.content.pm.PackageInfoCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.doOnPreDraw
import java.lang.reflect.Method

fun Application.easterEgg() = registerActivityLifecycleCallbacks(EasterEggRunner())

class EasterEggRunner(
    private val easterEggClzName: Array<String> = arrayOf(EasterEgg::class.java.name)
) :
    Application.ActivityLifecycleCallbacks {
    var systemWindowInsetTop: Int = 0
    override fun onActivityStarted(activity: Activity) {
        val parent = activity.findViewById<ViewGroup>(Window.ID_ANDROID_CONTENT)
        if (parent.findViewWithTag<View>(EASTER_EGG_VIEW_TAG) != null)
            return

        val easterEggButton = with(activity) {
            TextView(this).apply {
                setOnApplyWindowInsetsListener { _, insets ->
                    systemWindowInsetTop = WindowInsetsCompat.toWindowInsetsCompat(insets)
                        .getInsets(WindowInsetsCompat.Type.systemBars()).top
                    insets
                }
                doOnPreDraw {
                    val locationInWindow = IntArray(2)
                    getLocationInWindow(locationInWindow)
                    if (locationInWindow[1] == 0) y = systemWindowInsetTop.toFloat()
                }
                val versionCode = PackageInfoCompat.getLongVersionCode(
                    packageManager.getPackageInfo(
                        packageName,
                        0
                    )
                )
                val versionName = packageManager.getPackageInfo(packageName, 0).versionName

                @SuppressLint("SetTextI18n")
                text = "EasterEgg::$versionName::$versionCode"
                tag = EASTER_EGG_VIEW_TAG
                setTextColor(0x55ff0000)
                textSize = 9f // sp
                setBackgroundColor(0x5500ff00)
            }.also {
                parent.addView(
                    it,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        val classMethods = easterEggClzName.flatMap {
            getMethods(it)
        }

        val adapter = SimpleAdapter(
            activity,
            classMethods,
            android.R.layout.simple_list_item_2,
            arrayOf(KEY_CLASS_NAME, KEY_METHOD_NAME),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        easterEggButton.setOnClickListener {
            AlertDialog.Builder(activity)
                .setAdapter(adapter) { dialog, which ->
                    val classMethod =
                        (dialog as AlertDialog).listView.getItemAtPosition(which) as Map<*, *>
                    invokeMethod(
                        classMethod[KEY_CLASS] as Class<*>,
                        classMethod[KEY_METHOD] as Method,
                        activity
                    )
                }
                .show()
        }
    }

    private fun getMethods(clz: String) = runCatching {
        Class.forName(clz).run {
            methods.filter { it.declaringClass == this }
                .filter { it.returnType == Void.TYPE }
                .filterNot { it.name.contains("$") }
                .map {
                    mapOf(
                        KEY_CLASS to this,
                        KEY_METHOD to it,
                        KEY_CLASS_NAME to simpleName,
                        KEY_METHOD_NAME to it.name
                    )
                }
                .toList()
        }
    }.getOrDefault(emptyList())

    private fun invokeMethod(clazz: Class<*>, method: Method, activity: Activity) {
        val constructor = clazz.getConstructor(Activity::class.java)
        val receiver = constructor.newInstance(activity)
        method.invoke(receiver)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
    override fun onActivityResumed(activity: Activity) {}
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityStopped(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    override fun onActivityDestroyed(activity: Activity) {}

    companion object {
        private const val EASTER_EGG_VIEW_TAG = "show_me_the_money"
        private const val KEY_CLASS = "class"
        private const val KEY_METHOD = "method"
        private const val KEY_CLASS_NAME = "className"
        private const val KEY_METHOD_NAME = "methodName"
    }
}
