package com.teamtuna.emotionaldiary.presentation.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.teamtuna.emotionaldiary.domain.entity.DailyEmotion
import com.teamtuna.emotionaldiary.domain.entity.Emotion
import com.teamtuna.emotionaldiary.presentation.util.bg_accent
import com.teamtuna.emotionaldiary.presentation.util.icon
import java.time.LocalDateTime
import kotlin.random.Random

@ExperimentalCoilApi
@Composable
fun EmotionDay(
    text: String,
    style: TextStyle = TextStyle(),
    dailyEmotion: DailyEmotion? = dummyDailyEmotion()
) {
    // val url = "https://upload.wikimedia.org/wikipedia/commons/thumb/" +
    //     "e/e6/Noto_Emoji_KitKat_263a.svg/1200px-Noto_Emoji_KitKat_263a.svg.png"

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.background(colorResource(dailyEmotion?.emotion.bg_accent).copy(alpha = 0.3f))
    ) {
        Text(
            text,
            Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = style
        )

        if (dailyEmotion != null) {
            Image(
                painter = rememberImagePainter(
                    data = dailyEmotion.emotion.icon,
                    // builder = {
                    //     transformations(CircleCropTransformation())
                    // },
                ),
                contentDescription = dailyEmotion.emotion.name,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
        }
    }
}

fun dummyDailyEmotion(): DailyEmotion? = if (Random.nextInt(3) == 0)
    null
else
    DailyEmotion(
        -1,
        Emotion.values()[Random.nextInt(5)],
        LocalDateTime.now(),
        null,
        null,
        ""
    )
