package com.teamtuna.emotionaldiary.repository

import com.nhaarman.mockitokotlin2.whenever
import com.teamtuna.emotionaldiary.datasource.LocalDataSource
import com.teamtuna.emotionaldiary.entity.Emotional
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

    private lateinit var repository: EmotionalRepository

    private lateinit var testEmotion : Emotional

    @BeforeEach
    fun setUp() {
        repository = EmotionalRepositoryImpl(localDataSource)
    }


    @DisplayName("Repository에서 Add시 Local에 같은 Emotional이 저장")
    @Test
    fun addTest() {
        testEmotion = Emotional.JOY
        whenever(localDataSource.add(testEmotion, "Test")).thenReturn(1)
        val actual = repository.add(testEmotion,"Test")


        Mockito.verify(localDataSource).add(testEmotion, "Test")
        assertEquals(1, actual)
    }
}