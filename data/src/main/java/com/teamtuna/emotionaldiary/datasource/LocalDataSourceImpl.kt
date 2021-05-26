package com.teamtuna.emotionaldiary.datasource

import com.teamtuna.emotionaldiary.entity.Emotion

class LocalDataSourceImpl : LocalDataSource {

    override fun test() {

    }

    override fun add(emotion: Emotion, reason: String): Int {
        return 1
    }
}