package com.teamtuna.emotionaldiary.compose.hellocompose

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.teamtuna.emotionaldiary.theme.AppTheme

@Preview
@Composable
fun HelloComposeApp() {
    AppTheme {
        ProvideWindowInsets {
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setSystemBarsColor(Color(0x55ff0000))
            }

            Text(
                text = """Hello Compose 1
Hello Compose 2
Hello Compose 3
Hello Compose 4
Hello Compose 5
Hello Compose 6
Hello Compose 7
Hello Compose 8
Hello Compose 9
Hello Compose 0
Hello Compose 1
Hello Compose 2
Hello Compose 3
Hello Compose 4
Hello Compose 5
Hello Compose 6
Hello Compose 7
Hello Compose 8
Hello Compose 9
Hello Compose 0
Hello Compose 1
Hello Compose 2
Hello Compose 3
Hello Compose 4
Hello Compose 5
Hello Compose 6
Hello Compose 7
Hello Compose 8
Hello Compose 9
Hello Compose 0
Hello Compose 1
Hello Compose 2
Hello Compose 3
Hello Compose 4
Hello Compose 5
Hello Compose 6
Hello Compose 7
Hello Compose 8
Hello Compose 9
Hello Compose 0
Hello Compose 1
""".trimMargin()
            )
        }
    }
}
