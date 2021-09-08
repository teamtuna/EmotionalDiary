package com.teamtuna.emotionaldiary.write

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.teamtuna.emotionaldiary.compose.theme.EmotionalDiaryTheme
import com.teamtuna.emotionaldiary.entity.DailyEmotion
import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.presentation.R
import java.time.LocalDateTime

@Preview
@Composable
fun WriteCompose(writeViewModel: WriteViewModel = viewModel()) {
    EmotionalDiaryTheme {
        ProvideWindowInsets {
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setSystemBarsColor(Color(0x55ff0000))
            }
            // TODO 제거할 코드
            val emotion = DailyEmotion(
                id = 0L,
                date = LocalDateTime.now(),
                emotion = Emotion.FEAR,
                reason = "TEST"
            )

            val scrollState = rememberLazyListState()
            Scaffold(
                topBar = {
                    InsetAwareTopAppBar(
                        title = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(align = Alignment.CenterHorizontally)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.app_name),
                                    style = MaterialTheme.typography.subtitle2,
                                    color = LocalContentColor.current,
                                    modifier = Modifier
                                        .weight(1.5f)
                                )
                            }
                        },
                        navigationIcon = {
                            IconButton(onClick = {}) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "",
                                    tint = MaterialTheme.colors.primary
                                )
                            }
                        },
                        elevation = if (!scrollState.isScrollInProgress) 0.dp else 4.dp,
                        backgroundColor = MaterialTheme.colors.surface
                    )
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .scrollable(state = scrollState, orientation = Orientation.Vertical)
                ) {
                    ImageCard(emotion)
                    WriteTextField(state = remember { mutableStateOf(TextFieldValue(text = emotion.reason)) },
                    scrollableState = scrollState)
                }
            }
        }
    }
}
