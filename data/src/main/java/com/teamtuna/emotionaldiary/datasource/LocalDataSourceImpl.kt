package com.teamtuna.emotionaldiary.datasource

import com.teamtuna.emotionaldiary.entity.Emotional

class LocalDataSourceImpl : LocalDataSource {

    override fun test() {

    }

    override fun add(emotional: Emotional, reason: String): Int {
        return 1
    }
}