package com.teamtuna.emotionaldiary.fcm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import com.teamtuna.emotionaldiary.R

object EmotionalDiaryNotificationChannelManager {
    private const val GROUP_ID = "EmotionalDiary"
    private const val GROUP_NAME = "감정일기"

    @RequiresApi(Build.VERSION_CODES.N)
    enum class NotificationChannelDefine(
        var groupId: String,
        var groupName: String,
        val channelName: String,
        var importance: Int,
        var description: String,
        var lightColor: Int,
        var lockScreenVisibility: Int
    ) {
        Emotional(
            GROUP_ID,
            GROUP_NAME,
            "Common Channel",
            NotificationManager.IMPORTANCE_DEFAULT,
            "기본체널(Common Channel)",
            Color.BLUE,
            Notification.VISIBILITY_PUBLIC
        ),
        Diary(
            GROUP_ID,
            GROUP_NAME,
            "Diary  Channel",
            NotificationManager.IMPORTANCE_HIGH,
            "Diary Channel 입니다.",
            Color.RED,
            Notification.VISIBILITY_SECRET
        ),
    }

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            return

        // default channel check
        val defaultChannel = NotificationChannelDefine.Emotional // set default channel defind hear
        assert(context.getString(R.string.default_notification_channel_id) == defaultChannel.name)
        assert(
            context.getString(
                R.string.default_notification_channel_name
            ) == defaultChannel.channelName
        )

        // create channel
        context.getSystemService(NotificationManager::class.java)?.let { mn ->
            NotificationChannelDefine.values().forEach {
                mn.createNotificationChannelGroup(
                    NotificationChannelGroup(
                        it.groupId,
                        it.groupName
                    )
                )

                val channel = NotificationChannel(it.name, it.channelName, it.importance).apply {
                    description = it.description
                    group = it.groupId
                    lightColor = it.lightColor
                    lockscreenVisibility = it.lockScreenVisibility
                }
                mn.createNotificationChannel(channel)
            }
        }
    }

    @Suppress("unused")
    fun deleteNotificationChannel(context: Context, channel: NotificationChannelDefine) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            return
        context.getSystemService(NotificationManager::class.java)
            ?.deleteNotificationChannel(channel.name)
    }
}
