package com.teamtuna.emotionaldiary.usecase

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.whenever
import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.entity.Result
import com.teamtuna.emotionaldiary.repository.EmotionRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExperimentalCoroutinesApi
@DisplayName("감정추가에서 ")
@ExtendWith(MockitoExtension::class)
internal class EmotionAddUseCaseTest {

    @Mock
    private lateinit var repository: EmotionRepository

    @Test
    @DisplayName("기쁨이를 추가 한경우 EmotionalRepository.add가 호출되는지확인")
    fun addUseCase() = runBlockingTest {
        //given
        val answers = Result.Success(1L)
        whenever(repository.add(Emotion.JOY, "기쁨이")).thenReturn(answers)
        val addUsecase = EmotionAddUseCase(repository)

        //val getUsecase = EmotionalGetUseCase(repository)

        //when
        val actual /*id*/ = addUsecase(Emotion.JOY, "기쁨이")

        //then
        Mockito.verify(repository, times(1)).add(Emotion.JOY, "기쁨이")
        assertThat((actual as Result.Success).data, equalTo(1L))
    }
}
