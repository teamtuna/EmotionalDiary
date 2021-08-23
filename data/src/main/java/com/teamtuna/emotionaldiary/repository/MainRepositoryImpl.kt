package com.teamtuna.emotionaldiary.repository

import androidx.core.util.lruCache
import com.teamtuna.emotionaldiary.datasource.LocalDataSource
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val local: LocalDataSource
) : MainRepository {
    val cache = lruCache<String, String>(1)

    override fun test(): Result<String> = kotlin.runCatching {
        if (cache["test"].isNullOrEmpty()) {
            cache.put("test", "cached test")
            Result.success("remote test : ${cache.get("test")}")
        } else {
            Result.success("local test : ${cache.get("test")}")
        }
    }.getOrElse {
        Result.failure(it)
    }
}
