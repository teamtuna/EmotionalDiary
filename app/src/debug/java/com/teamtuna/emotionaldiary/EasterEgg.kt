package com.teamtuna.emotionaldiary

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.log.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.teamtuna.emotionaldiary.db.EmotionRoomDatabase
import com.teamtuna.emotionaldiary.entity.DailyEmotion
import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.entity.onFailure
import com.teamtuna.emotionaldiary.entity.onSuccess
import com.teamtuna.emotionaldiary.repository.EmotionRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import kotlin.random.Random

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

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface EmotionRepositoryEntryPoint {
        fun emotionRepositoryProvide(): EmotionRepository
        fun emotionRoomDatabaseProvide(): EmotionRoomDatabase
    }

    private val emotionRepository =
        EntryPointAccessors.fromApplication(activity, EmotionRepositoryEntryPoint::class.java)
            .emotionRepositoryProvide()

    private val emotionDao =
        EntryPointAccessors.fromApplication(activity, EmotionRepositoryEntryPoint::class.java)
            .emotionRoomDatabaseProvide().fcmDao()

    fun randomDataCreator() {
        Log.e(emotionRepository)
        (activity as ComponentActivity).lifecycleScope.launch {
            emotionRepository.add(randomDailyEmotion)
                .onFailure {
                    Log.w(it?.title, it?.description)
                }
                .onSuccess {
                    Log.e(it)
                }
        }
    }

    fun checkRoomData() {
        Log.e(emotionDao)
        (activity as ComponentActivity).lifecycleScope.launch {
            Pager(PagingConfig(5)) {
                emotionDao.getEmotional()
            }.flow.collectLatest {
                emotionDao.getAll().forEach {
                    Log.i(it)
                }
            }
        }
    }
}

val randomDailyEmotion
    get() = DailyEmotion(
        0,
        Emotion.values()[Random.nextInt(5)],
        LocalDateTime.now().plusDays(Random.nextLong(-15, 15)),
        "https://picsum.photos/id/${Random.nextInt(100)}/100/100",
        "geo:${Random.latitude},${Random.longitude}",
        ('a'..'z').shuffled().take(Random.nextInt(4, 'z' - 'a')).toCharArray().joinToString("")
    )

private val Random.latitude: String
    get() = "%.4f".format(nextDouble(-90.0, +90.0))

private val Random.longitude: String
    get() = "%.4f".format(nextDouble(-90.0, +90.0))
