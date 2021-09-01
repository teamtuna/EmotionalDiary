package com.teamtuna.emotionaldiary.write

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.teamtuna.emotionaldiary.compose.theme.EmotionalDiaryTheme

@Preview
@Composable
fun WriteCompose() {
    EmotionalDiaryTheme {
        ProvideWindowInsets {
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setSystemBarsColor(Color(0x55ff0000))
            }

            Column {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                ) {
                    // Image(
                    //     painter = painterResource(id = R.drawable.splash),
                    //     contentDescription = ""
                    // )
                }
                TitleText()
                LocationText()
                DiaryInputField()
            }
        }
    }
}

@Composable
fun TitleText() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        // Image(
        //     painter = painterResource(id = R.drawable.emotional_bg_disgust),
        //     contentDescription = ""
        // )
        Text(text = "오늘의 감정은?")
        Text(text = "기쁨")
    }
}

@Composable
fun LocationText() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        // Image(
        //     painter = painterResource(id = R.drawable.emotional_bg_joy),
        //     contentDescription = ""
        // )
        Icon(
            imageVector = Icons.Filled.Save,
            contentDescription = "",
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(text = "일기의 위치는?")
        TextButton(onClick = {}) {
            Text(text = "위치 설정 하기")
        }
    }
}

@Composable
fun DiaryInputField() {
    TextField(modifier = Modifier.fillMaxWidth(), value = "", onValueChange = {

    })
}
