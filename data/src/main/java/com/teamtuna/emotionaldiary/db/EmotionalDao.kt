package com.teamtuna.emotionaldiary.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teamtuna.emotionaldiary.entity.UniqId

@Dao
interface EmotionalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmotional(entity: EmotionalEntity): UniqId

    @Query("SELECT * FROM EmotionalEntity WHERE id = :id")
    fun getEmotional(id: UniqId): EmotionalEntity?

    @Delete
    fun deleteEmotional(entity: EmotionalEntity)

    @Query("SELECT * FROM EmotionalEntity")
    fun getEmotional(): PagingSource<Int, EmotionalEntity>

    @Query("SELECT * FROM EmotionalEntity")
    suspend fun getAll(): List<EmotionalEntity>
}
