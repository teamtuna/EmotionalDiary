package com.teamtuna.emotionaldiary.datasource

import com.teamtuna.emotionaldiary.entity.Emotional

interface LocalDataSource {
    fun test()
    fun add(emotional: Emotional, reason : String) : Int
}