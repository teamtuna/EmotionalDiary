package com.teamtuna.emotionaldiary.fcm


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.log.Log
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.teamtuna.emotionaldiary.R


const val IS_HANDLE_MESSAGE_WITHIN_10_SECONDS = true

class EmotionalDiaryFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        Log.d("Refreshed token: $token")
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String?) {
        Log.d("sendRegistrationTokenToServer($token)")

    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("From: ${remoteMessage.from}")

        if (remoteMessage.data.isNotEmpty()) {
            Log.d("Message data payload: ${remoteMessage.data}")

            if (IS_HANDLE_MESSAGE_WITHIN_10_SECONDS) {
                handleNow()
            } else {
                scheduleJob()
            }
        }

        remoteMessage.notification?.dump()
    }

    private fun handleNow() {
        Log.d("Short lived task is done.")
        sendNotification("text body")
    }

    private fun scheduleJob() {
        val work = OneTimeWorkRequest.Builder(NotificationWorker::class.java).build()
        WorkManager.getInstance(applicationContext).beginWith(work).enqueue()
    }


    private fun sendNotification(messageBody: String) {
        val intent = packageManager.getLaunchIntentForPackage(packageName)!!

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0 /* Request code */, intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_stat_ic_notification)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }

    companion object {
        fun getToken() {
            FirebaseMessaging.getInstance().token.addOnCompleteListener {
                if (!it.isSuccessful) {
                    Log.w("Fetching FCM registration token failed", it.exception)
                    return@addOnCompleteListener
                }

                // Get new FCM registration token
                Log.d("FCM registration token", it.result)
            }
        }
    }
}

private fun RemoteMessage.Notification.dump() {
    Log.d(
        //@formatter:off
        "\ntitle                  : " + title                     //: String? = null
        + "\nbody                   : " + body                      //: String? = null
        + "\nicon                   : " + icon                      //: String? = null
        + "\nimageUrl               : " + imageUrl                  //: String? = null
        + "\nsound                  : " + sound                     //: String? = null
        + "\ntag                    : " + tag                       //: String? = null
        + "\ncolor                  : " + color                     //: String? = null
        + "\nclickAction            : " + clickAction               //: String? = null
        + "\nchannelId              : " + channelId                 //: String? = null
        + "\nlink                   : " + link                      //: Uri? = null
        + "\nticker                 : " + ticker                    //: String? = null
        + "\nnotificationPriority   : " + notificationPriority      //: Int? = null
        + "\nvisibility             : " + visibility                //: Int? = null
        + "\nnotificationCount      : " + notificationCount         //: Int? = null
        + "\nlightSettings          : " + lightSettings             //: IntArray
        + "\neventTime              : " + eventTime                 //: Long? = null
        + "\nsticky                 : " + sticky                    //= false
        + "\nlocalOnly              : " + localOnly                 //= false
        + "\ndefaultSound           : " + defaultSound              //= false
        + "\ndefaultLightSettings   : " + defaultLightSettings      //= false
        + "\nvibrateTimings         : " + vibrateTimings            //: LongArray
        + "\nbodyLocalizationKey    : " + bodyLocalizationKey
        + "\nbodyLocalizationArgs   : " + bodyLocalizationArgs
        + "\ntitleLocalizationKey   : " + titleLocalizationKey
        + "\ntitleLocalizationArgs  : " + titleLocalizationArgs
        + "\ndefaultVibrateSettings : " + defaultVibrateSettings
    )
}




