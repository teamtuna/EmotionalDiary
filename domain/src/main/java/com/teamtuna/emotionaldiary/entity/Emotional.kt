package com.teamtuna.emotionaldiary.entity

sealed class Emotional {
    object JOY : Emotional()
    object SADNESS : Emotional()
    object ANGER : Emotional()
    object DISGUST : Emotional()
    object FEAR : Emotional()
}
