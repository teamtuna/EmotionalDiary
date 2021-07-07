package com.teamtuna.emotionaldiary.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmotionalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmotional(entity: EmotionalEntity): Long

    @Query("SELECT * FROM EmotionalEntity WHERE id = :id")
    fun getEmotional(id: Long): EmotionalEntity?
}