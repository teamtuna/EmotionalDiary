package com.teamtuna.emotionaldiary.usecase

import com.teamtuna.emotionaldiary.repository.MainRepository
import javax.inject.Inject

class MainUseCase @Inject constructor(
    private val repository: MainRepository
) {
    operator fun invoke(): Result<String> {
        return repository.test()
    }
}
