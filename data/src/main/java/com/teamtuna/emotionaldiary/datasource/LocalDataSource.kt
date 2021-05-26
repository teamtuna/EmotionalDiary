package com.teamtuna.emotionaldiary.datasource

import com.teamtuna.emotionaldiary.entity.Emotion

interface LocalDataSource {
    fun test()
    fun add(emotion: Emotion, reason : String) : Int
}