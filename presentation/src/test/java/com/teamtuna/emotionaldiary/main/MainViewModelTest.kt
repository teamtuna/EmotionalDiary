package com.teamtuna.emotionaldiary.main

import com.teamtuna.emotionaldiary.repository.MainRepository
import com.teamtuna.emotionaldiary.usecase.MainUseCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainUseCase: MainUseCase
    private var mainRepository: MainRepository = mock(MainRepository::class.java)

    @Before
    fun setupViewModel() {
        mainUseCase = MainUseCase(mainRepository)
        mainViewModel = MainViewModel(mainUseCase)
    }

    @Test
    fun `mainViewModel 에서 test()를 호출하면 true를 반환한다`() {
        // given
        `when`(mainRepository.test()).thenReturn(Result.success(Unit))

        // when
        val testReturn = mainViewModel.test()

        // then
        assert(testReturn.isSuccess)
    }
}
