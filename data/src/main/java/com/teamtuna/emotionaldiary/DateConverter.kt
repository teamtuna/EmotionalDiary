package com.teamtuna.emotionaldiary

import androidx.room.TypeConverter
import java.util.Date

object DateConverter {

    @TypeConverter
    fun toDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}
