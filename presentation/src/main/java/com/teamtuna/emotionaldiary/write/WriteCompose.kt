package com.teamtuna.emotionaldiary.write

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.teamtuna.emotionaldiary.compose.theme.EmotionalDiaryTheme
import com.teamtuna.emotionaldiary.entity.DailyEmotion
import com.teamtuna.emotionaldiary.entity.Emotion
import java.time.LocalDateTime

@Preview
@Composable
fun WriteCompose() {
    EmotionalDiaryTheme {
        ProvideWindowInsets {
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setSystemBarsColor(Color(0x55ff0000))
            }

            val emotion = DailyEmotion(
                id = 0L,
                imageUrl = "",
                date = LocalDateTime.now(),
                emotion = Emotion.FEAR,
                reason = "TEST"
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                ImageCard(emotion)
                WriteTextField(remember { mutableStateOf(TextFieldValue(text = emotion.reason)) })
            }
        }
    }
}
