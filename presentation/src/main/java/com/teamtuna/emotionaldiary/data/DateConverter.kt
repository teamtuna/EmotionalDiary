package com.teamtuna.emotionaldiary.data

import androidx.room.TypeConverter
import java.time.LocalDateTime

object DateConverter {

    @TypeConverter
    fun toDate(value: Long?): LocalDateTime? {
        return value?.let { it.toLocalDateTime() }
    }

    @TypeConverter
    fun fromDate(date: LocalDateTime?): Long? {
        return date?.getTime()
    }
}
