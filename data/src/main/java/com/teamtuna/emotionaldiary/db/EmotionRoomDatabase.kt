package com.teamtuna.emotionaldiary.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.teamtuna.emotionaldiary.DateConverter

@Database(entities = [EmotionalEntity::class], version = 2, exportSchema = true)
@TypeConverters(DateConverter::class)
abstract class EmotionRoomDatabase : RoomDatabase() {
    abstract fun fcmDao(): EmotionalDao
}
