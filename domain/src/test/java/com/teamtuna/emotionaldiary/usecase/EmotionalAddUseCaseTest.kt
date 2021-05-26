package com.teamtuna.emotionaldiary.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.whenever
import com.teamtuna.emotionaldiary.entity.Emotional
import com.teamtuna.emotionaldiary.repository.EmotionalRepository
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito

@DisplayName("감정추가에서 ")
internal class EmotionalAddUseCaseTest {
    private val repository: EmotionalRepository by lazy { mock() }

    @BeforeEach
    fun setUp() {
        whenever(repository.add(Emotional.JOY, "기쁨이")).thenReturn(1)
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    @DisplayName("기쁨이를 추가 한경우 EmotionalRepository.add가 호출되는지확인")
    fun addUseCase() {
        //given
        val addUsecase = EmotionalAddUseCase(repository)
        //val getUsecase = EmotionalGetUseCase(repository)

        //when
        val actual /*id*/ = addUsecase(Emotional.JOY, "기쁨이")

        //then
        Mockito.verify(repository, times(1)).add(Emotional.JOY, "기쁨이")
        assertThat(actual, equalTo(1))
    }
}