package com.teamtuna.emotionaldiary.entity

sealed class Result<T> {
    class Success<T>(val data: T) : Result<T>()
    class Fail<T>(val errorModel: ErrorModel?) : Result<T>()
}

data class ErrorModel(val title: String? = "unKnown error", val description: String? = null)
