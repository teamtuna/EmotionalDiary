package com.teamtuna.emotionaldiary.repository

import com.nhaarman.mockitokotlin2.whenever
import com.teamtuna.emotionaldiary.datasource.LocalDataSource
import com.teamtuna.emotionaldiary.db.EmotionalEntity
import com.teamtuna.emotionaldiary.entity.DailyEmotion
import com.teamtuna.emotionaldiary.entity.Emotion
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

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
        assertEquals(1, actual)
    }

    @DisplayName("Repository에서 get할 때 ")
    @Test
    fun getTest() = runBlocking {
        whenever(localDataSource.get(1)).thenReturn(
            EmotionalEntity(
                id = 1,
                emotion = Emotion.JOY,
                reason = "No Emotion"
            )
        )

        val actual = repository.get(1)

        val expect = DailyEmotion(
            id = 1,
            emotion = Emotion.JOY,
            reason = "No Emotion"
        )
        Mockito.verify(localDataSource).get(1)
        assertEquals(expect, actual)
    }
}
