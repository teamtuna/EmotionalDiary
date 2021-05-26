package com.teamtuna.emotionaldiary.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EmotionalEntity::class], version = 1)
abstract class EmotionRoomDatabase : RoomDatabase() {
    abstract fun fcmDao(): EmotionalDao
}
