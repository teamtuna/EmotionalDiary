package com.teamtuna.emotionaldiary.data.repository

import com.nhaarman.mockitokotlin2.whenever
import com.teamtuna.emotionaldiary.data.datasource.LocalDataSource
import com.teamtuna.emotionaldiary.data.db.EmotionalEntity
import com.teamtuna.emotionaldiary.domain.entity.Emotion
import com.teamtuna.emotionaldiary.domain.entity.Result
import com.teamtuna.emotionaldiary.domain.repository.EmotionRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
internal class EmotionalRepositoryTest {

    @Mock
    private lateinit var localDataSource: LocalDataSource

    private lateinit var repository: EmotionRepository

    private lateinit var testEmotion: Emotion

    @BeforeEach
    fun setUp() {
        repository = EmotionRepositoryImpl(localDataSource)
    }

    @DisplayName("Repository에서 Add시 Local에 같은 Emotional이 저장")
    @Test
    fun addTest() = runBlocking {
        testEmotion = Emotion.JOY
        whenever(localDataSource.add(testEmotion, "Test")).thenReturn(1)
        val actual = repository.add(testEmotion, "Test")

        Mockito.verify(localDataSource).add(testEmotion, "Test")

        assertTrue(actual is Result.Success)
        assertEquals(1, (actual as Result.Success).data)
    }

    @DisplayName("Repository에서 Add시 Local에 같은 날짜의 Emotional이 저장")
    @Test
    fun addTest2() = runBlocking {
        testEmotion = Emotion.JOY
        val currentDate = LocalDateTime.now()
        whenever(localDataSource.add(testEmotion, currentDate, "Test")).thenReturn(1)
        val actual = repository.add(testEmotion, currentDate, "Test")

        Mockito.verify(localDataSource).add(testEmotion, currentDate, "Test")

        assertTrue(actual is Result.Success)
        assertEquals(1, (actual as Result.Success).data)
    }

    @DisplayName("Repository에서 Add시 Local에 같은 날짜의 Emotional이 저장")
    @Test
    fun addTest3() = runBlocking {
        val testData = com.teamtuna.emotionaldiary.domain.entity.DailyEmotion.EMPTY
        val currentDate = LocalDateTime.now()
        whenever(localDataSource.add(testData)).thenReturn(1)
        val actual = repository.add(testData)

        Mockito.verify(localDataSource).add(testData)

        assertTrue(actual is Result.Success)
        assertEquals(1, (actual as Result.Success).data)
    }

    @DisplayName("Repository에서 get할 때 ")
    @Test
    fun getTest() = runBlocking {
        val currentDate = LocalDateTime.now()
        whenever(localDataSource.get(1)).thenReturn(
            EmotionalEntity(
                id = 1,
                emotion = Emotion.JOY,
                date = currentDate,
                location = null,
                photo = null,
                reason = "No Emotion"
            )
        )

        val actual = repository.get(1)

        assertTrue(actual is Result.Success)

        val expect = com.teamtuna.emotionaldiary.domain.entity.DailyEmotion(
            id = 1,
            emotion = Emotion.JOY,
            date = currentDate,
            location = null,
            photo = null,
            reason = "No Emotion"
        )
        Mockito.verify(localDataSource).get(1)
        assertEquals(expect, (actual as Result.Success).data)
    }

    @DisplayName("Repository Replace")
    @Test
    fun replaceTest() = runBlocking {
        val currentDate = LocalDateTime.now()
        whenever(
            localDataSource.replace(
                com.teamtuna.emotionaldiary.domain.entity.DailyEmotion(1, Emotion.FEAR, currentDate, null, null, "Yes")
            )
        ).thenReturn(true)

        val actual = repository.replace(
            com.teamtuna.emotionaldiary.domain.entity.DailyEmotion(1, Emotion.FEAR, currentDate, null, null, "Yes")
        )

        assertTrue(actual is Result.Success)

        Mockito.verify(localDataSource).replace(
            com.teamtuna.emotionaldiary.domain.entity.DailyEmotion(1, Emotion.FEAR, currentDate, null, null, "Yes")
        )
        assertTrue((actual as Result.Success).data)
    }

    @DisplayName("Repository Delete")
    @Test
    fun deleteTest() = runBlocking {
        whenever(localDataSource.add(Emotion.FEAR, "Yes"))
            .thenReturn(1L)

        whenever(localDataSource.delete(1L))
            .thenReturn(Unit)

        val addResult = repository.add(Emotion.FEAR, "Yes")
        repository.delete((addResult as Result.Success).data)

        Mockito.verify(localDataSource).delete(addResult.data)
    }
}
