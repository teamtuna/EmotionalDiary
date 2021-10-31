package com.teamtuna.emotionaldiary

import com.google.common.truth.Truth
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

class EasterEggTest {
    @Test
    fun calendar_first_day_of_month() {
        // given
        val data = LocalDateTime.parse("2021-10-14T00:00:00")

        // when
        val actual = firstDayOfMonth(data)

        // then
        Truth.assertThat(actual.toLocalDate()).isEqualTo(LocalDate.parse("2021-10-01"))
    }

    @Test
    fun calendar_first_day_of_week() {
        // given
        val data = LocalDateTime.parse("2021-10-14T00:00:00")

        // when
        val actual = firstDayOfWeek(data)

        // then
        Truth.assertThat(actual.toLocalDate()).isEqualTo(LocalDate.parse("2021-10-10"))
    }

    @Test
    fun calendar_first_day_of_month_with_week() {
        // given
        val data = LocalDateTime.parse("2021-10-14T00:00:00")

        // when
        val firstDayOfMonth = firstDayOfMonth(data)
        val actual = firstDayOfWeek(firstDayOfMonth)

        // then
        Truth.assertThat(actual.toLocalDate()).isEqualTo(LocalDate.parse("2021-09-26"))
    }

    @Test
    fun calendar_last_day_of_month() {
        // given
        val data = LocalDateTime.parse("2021-10-14T00:00:00")

        // when
        val actual = lastDayOfMonth(data)

        // then
        Truth.assertThat(actual.toLocalDate()).isEqualTo(LocalDate.parse("2021-10-31"))
    }

    @Test
    fun calendar_last_day_of_week() {
        // given
        val data = LocalDateTime.parse("2021-10-14T00:00:00")

        // when
        val actual = lastDayOfWeek(data)

        // then
        Truth.assertThat(actual.toLocalDate()).isEqualTo(LocalDate.parse("2021-10-16"))
    }
}
