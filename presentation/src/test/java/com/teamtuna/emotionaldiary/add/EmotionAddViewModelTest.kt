package com.teamtuna.emotionaldiary.add

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.whenever
import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.entity.Result
import com.teamtuna.emotionaldiary.repository.EmotionRepository
import com.teamtuna.emotionaldiary.usecase.EmotionAddByDateUseCase
import com.teamtuna.emotionaldiary.usecase.EmotionAddUseCase
import com.teamtuna.util.InstantExecutorExtension
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@DisplayName("감정추가 뷰모델 테스트")
@ExtendWith(MockitoExtension::class, InstantExecutorExtension::class)
internal class EmotionAddViewModelTest {

    private lateinit var viewModel: EmotionAddViewModel

    @Mock
    private lateinit var emotionRepository: EmotionRepository

    /**
     * 구글에서 제공하는 AAC를 위한 Rule
     * androidx.arch.core:core-testing:2.1.0 에 포함되어 있음 참고
     * Rule을 사용하면 @Before, @After마다 자동으로 수행
     *
     * @get:Rule
     * var instantExecutorRule = InstantTaskExecutorRule()
     *
     *
     * JUnit 5 에서 사용하려면 https://stackoverflow.com/a/51012642 참고
     * @ExtendWith(MockitoExtension::class, InstantExecutorExtension::class)
     */
    // var instantExecutorRule = InstantExecutorExtension()

    @BeforeEach
    fun setUp() {
        viewModel = EmotionAddViewModel(
            EmotionAddUseCase(emotionRepository),
            EmotionAddByDateUseCase(emotionRepository)
        )
    }

    @AfterEach
    fun tearDown() {
    }

    // org.junit.jupiter.api.Test 사용 필수 (Mock 초기화 안되는 이슈)
    @Test
    @DisplayName("기쁨이를 추가 한경우 EmotionAddViewModel.add가 호출되는지확인")
    fun add() = runBlockingTest {
        // given
        whenever(emotionRepository.add(
            Emotion.JOY, "기쁨이")
        ).thenReturn(Result.Success(1L))

        // when
        viewModel.add(Emotion.JOY, "기쁨이")

        // then
        Mockito.verify(emotionRepository, times(1)).add(Emotion.JOY, "기쁨이")
    }
}
