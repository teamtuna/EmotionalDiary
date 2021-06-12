package com.teamtuna.emotionaldiary.repository

import com.teamtuna.emotionaldiary.datasource.LocalDataSource

class MainRepositoryImpl(
    local: LocalDataSource,
    /*remote : RemoteDataSource*/
) : MainRepository {

    override fun test(): Result<Unit> {
        /*if(local.test())
            return local.test()
        else {
            val result = remote.test()
            local.add(result)
            return result
        }*/
        return Result.success(Unit)
    }
}