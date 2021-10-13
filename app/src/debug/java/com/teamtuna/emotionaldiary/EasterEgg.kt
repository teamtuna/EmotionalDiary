package com.teamtuna.emotionaldiary

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.log.Log
import android.os.Build
import android.text.Html
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.teamtuna.emotionaldiary.data.db.EmotionRoomDatabase
import com.teamtuna.emotionaldiary.domain.entity.DailyEmotion
import com.teamtuna.emotionaldiary.domain.entity.Emotion
import com.teamtuna.emotionaldiary.domain.entity.onFailure
import com.teamtuna.emotionaldiary.domain.entity.onSuccess
import com.teamtuna.emotionaldiary.domain.repository.EmotionRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStreamReader
import java.net.URL
import java.time.LocalDateTime
import java.time.temporal.WeekFields
import java.util.Locale
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

    @RequiresApi(Build.VERSION_CODES.N)
    fun blobDummyInsert() {
        (activity as ComponentActivity).lifecycleScope.launch(Dispatchers.IO) {
            emotionRepository.add(*fixedMonthDailyEmotions())
                .onFailure {
                    Log.w(it?.title, it?.description)
                }
                .onSuccess {
                    Log.e(it)
                }
        }
    }
}

fun firstDayOfMonth(date: LocalDateTime): LocalDateTime {
    return date.withDayOfMonth(1)
}

fun lastDayOfMonth(date: LocalDateTime): LocalDateTime {
    return firstDayOfMonth(date.plusMonths(1)).plusDays(-1)
}

fun firstDayOfWeek(date: LocalDateTime): LocalDateTime {
    val fieldISO = WeekFields.of(Locale.getDefault()).dayOfWeek()
    return date.with(fieldISO, 1)
}

fun lastDayOfWeek(date: LocalDateTime): LocalDateTime {
    val fieldISO = WeekFields.of(Locale.getDefault()).dayOfWeek()
    return date.with(fieldISO, 7)
}

@SuppressLint("NewApi")
@Suppress("BlockingMethodInNonBlockingContext")
suspend fun getDummyTextByDate(date: LocalDateTime) = withContext(Dispatchers.IO) {
    URL("https://ko.wikipedia.org/wiki/${date.monthValue}월_${date.dayOfMonth}일").openConnection().getInputStream().use {
        val text = InputStreamReader(it).readText()
        val eventHtml = text.substring(text.indexOf("<li>"), text.indexOf("</li>", text.indexOf("<li>")) + 5)
        val eventText = Html.fromHtml(eventHtml, Html.FROM_HTML_MODE_LEGACY)
        eventText.toString().trim()
    }
}

suspend fun fixedMonthDailyEmotions() = run {
    val startDate = firstDayOfWeek(firstDayOfMonth(LocalDateTime.now()))
    val lastDate = lastDayOfWeek(lastDayOfMonth(LocalDateTime.now()))
    var date = startDate


    val list = mutableListOf<DailyEmotion>()
    while (date <= lastDate) {
        val day = date.dayOfMonth
        val month = date.monthValue


        list += DailyEmotion(
            0,
            Emotion.values()[day % Emotion.values().size],
            date,
            "https://picsum.photos/id/$month$day/100/100",
            "geo:%.4f,%.4f".format(180.0 * (month.toLong() / 12.0 - 90.0), 360.0 * (day.toLong() / 31.0) - 180.0),
            getDummyTextByDate(date)
        )
        date = date.plusDays(Random.nextLong(1, 3))
        Log.e(list.last())
    }
    list.toTypedArray()
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
    get() = "%.4f".format(nextDouble(-180.0, +180.0))
