package com.teamtuna.emotionaldiary

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Period
import java.time.ZoneId
import java.util.Date

fun Date.convertInstant(): Instant {
    return Instant.ofEpochMilli(this.time)
}

fun Int.toLocalDateSecondTime(): LocalDateTime {
    return (this * 1000L).toLocalDateTime()
}

fun Int.toLocalDate(): LocalDate {
    return (this * 1000L).toLocalDate()
}

fun Int.toLocalTime(): LocalTime {
    return toLocalDateSecondTime().toLocalTime()
}

fun Int.timeInMills(): Long {
    return this.toLocalDateSecondTime().getTime()
}

fun Long.toLocalDateTime(): LocalDateTime {
    return LocalDateTime.ofInstant(Date(this).convertInstant(), ZoneId.systemDefault())
}

fun Long.toLocalDate(): LocalDate {
    return Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault()).toLocalDate()
}

fun Long.timeInSeconds(): Int {
    return ((this / 1000L).toInt())
}

fun LocalDateTime.getDayOfDateTime(): LocalDateTime {
    return this
        .withHour(0)
        .withMinute(0)
        .withSecond(0)
}

fun LocalDateTime.getDayOfTime(): Long {
    return this
        .withHour(0)
        .withMinute(0)
        .withSecond(0)
        .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}

fun LocalDateTime.getTime(): Long {
    return this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}

fun Long.isToday(): Boolean {
    val period = Period.between(LocalDate.now(), toLocalDateTime().toLocalDate())
    return period.years == 0 && period.months == 0 && period.days == 0
}

fun Long.isYesterday(): Boolean {
    val period = Period.between(LocalDate.now(), toLocalDateTime().toLocalDate())
    return period.years == 0 && period.months == 0 && period.days == -1
}

fun Long.isPastYear(): Boolean {
    return LocalDate.now().year > toLocalDateTime().toLocalDate().year
}

fun Long.beforeCurrentTime(): Boolean {
    return this < System.currentTimeMillis()
}

fun epoch(year: Int, month: Int, dayOfMonth: Int): Int {
    return LocalDateTime.now().withYear(year).withMonth(month).withDayOfMonth(dayOfMonth)
        .atZone(ZoneId.systemDefault()).toEpochSecond().toInt()
}
