package com.teamtuna.emotionaldiary.add

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.whenever
import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.entity.Result
import com.teamtuna.emotionaldiary.repository.EmotionRepository
import com.teamtuna.emotionaldiary.usecase.EmotionAddUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExperimentalCoroutinesApi
@DisplayName("감정추가 뷰모델 테스트")
@ExtendWith(MockitoExtension::class)
internal class EmotionAddViewModelTest {

    private lateinit var viewModel: EmotionAddViewModel

    @Mock
    private lateinit var emotionRepository: EmotionRepository

    @BeforeEach
    fun setUp() {
        viewModel = EmotionAddViewModel(
            EmotionAddUseCase(emotionRepository)
        )
    }

    @AfterEach
    fun tearDown() {
    }

    // org.junit.jupiter.api.Test 사용 필수 (Mock 초기화 안되는 이슈)
    @Test
    @DisplayName("기쁨이를 추가 한경우 EmotionAddViewModel.add가 호출되는지확인")
    fun add() = runBlockingTest {
        //given
        whenever(emotionRepository.add(Emotion.JOY, "기쁨이")).thenReturn(Result.Success(1L))

        //when
        viewModel.add(Emotion.JOY, "기쁨이")

        //then
        Mockito.verify(viewModel, times(1)).add(Emotion.JOY, "기쁨이")
    }
}