package com.teamtuna.emotionaldiary.timeline

import android.net.Uri
import com.teamtuna.emotionaldiary.entity.Emotion

object DataProvider {

    val diaryList = listOf(
        EmotionItem(
            emotion = Emotion.ANGER,
            diaryContent = "오늘은 화가 났다",
            imageUrl = Uri.parse("https://upload2.inven.co.kr/upload/2016/07/19/bbs/i10597390041.jpg")
        ),
        EmotionItem(
            emotion = Emotion.JOY,
            diaryContent = "오늘은 기뻣다",
            imageUrl = Uri.parse("https://spnimage.edaily.co.kr/images/photo/files/NP/S/2019/05/PS19052700158.jpg")
        ),
        EmotionItem(
            emotion = Emotion.SADNESS,
            diaryContent = "오늘은 슬프다",
            imageUrl = Uri.parse(
                "https://cdn.pixabay.com/photo/2020/10/31/17/31/sad-5701778_1280.png"
            )
        ),
        EmotionItem(
            emotion = Emotion.ANGER,
            diaryContent = "오늘은 화가 났다",
            imageUrl = Uri.parse("https://upload2.inven.co.kr/upload/2016/07/19/bbs/i10597390041.jpg")
        ),
        EmotionItem(
            emotion = Emotion.JOY,
            diaryContent = "오늘은 기뻣다",
            imageUrl = Uri.parse("https://spnimage.edaily.co.kr/images/photo/files/NP/S/2019/05/PS19052700158.jpg")
        ),
        EmotionItem(
            emotion = Emotion.SADNESS,
            diaryContent = "오늘은 슬프다",
            imageUrl = Uri.parse(
                "https://cdn.pixabay.com/photo/2020/10/31/17/31/sad-5701778_1280.png"
            )
        ),
        EmotionItem(
            emotion = Emotion.ANGER,
            diaryContent = "오늘은 화가 났다",
            imageUrl = Uri.parse("https://upload2.inven.co.kr/upload/2016/07/19/bbs/i10597390041.jpg")
        ),
        EmotionItem(
            emotion = Emotion.JOY,
            diaryContent = "오늘은 기뻣다",
            imageUrl = Uri.parse("https://spnimage.edaily.co.kr/images/photo/files/NP/S/2019/05/PS19052700158.jpg")
        ),
        EmotionItem(
            emotion = Emotion.SADNESS,
            diaryContent = "오늘은 슬프다",
            imageUrl = Uri.parse(
                "https://cdn.pixabay.com/photo/2020/10/31/17/31/sad-5701778_1280.png"
            )
        ),
    )
}
