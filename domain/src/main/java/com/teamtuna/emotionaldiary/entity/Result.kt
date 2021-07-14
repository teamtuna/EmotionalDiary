package com.teamtuna.emotionaldiary.entity

sealed class Result<T> {
    class Success<T>(val data: T) : Result<T>()
    class Fail<T>(val errorModel: ErrorModel?) : Result<T>()
}

inline fun <reified T> Result<T>.process(success: (T) -> Unit, error: (ErrorModel?) -> Unit) {
    when (this) {
        is Result.Success -> success(data)
        is Result.Fail -> error(errorModel)
    }
}

data class ErrorModel(val title: String? = "unKnown error", val description: String? = null)
