package com.teamtuna.emotionaldiary.data.db.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration1To2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE EmotionalEntity ADD COLUMN location TEXT")
        database.execSQL("ALTER TABLE EmotionalEntity ADD COLUMN photo TEXT")
    }
}
