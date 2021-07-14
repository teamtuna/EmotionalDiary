package com.teamtuna.emotionaldiary.fcm

import android.content.Context
import android.log.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        Log.d("Performing long running task in scheduled job")
        return Result.success()
    }
}
