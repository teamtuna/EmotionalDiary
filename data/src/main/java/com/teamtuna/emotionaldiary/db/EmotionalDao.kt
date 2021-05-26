package com.teamtuna.emotionaldiary.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface EmotionalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmotional(entity: EmotionalEntity): Long
}