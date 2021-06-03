package com.teamtuna.emotionaldiary.usecase;

import com.teamtuna.emotionaldiary.repository.MainRepository

class MainUseCase(
    private val repository: MainRepository
) {
    operator fun invoke(): Boolean {
        return repository.test()
    }

}
