package com.teamtuna.emotionaldiary.presentation.write

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamtuna.emotionaldiary.domain.entity.Emotion
import com.teamtuna.emotionaldiary.main.R

@Composable
fun ImageCard(uiState: WriteUiState, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 18.dp)
    ) {
        val imageModifier = Modifier
            .heightIn(min = 180.dp, max = 250.dp)
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium)
        Image(
            // TODO Glide로 로드?
            painter = painterResource(R.drawable.splash_icon),
            contentDescription = null, // decorative
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(16.dp))

        ImageText(
            painter = painterResource(
                id = when (uiState.emotion) {
                    Emotion.JOY -> R.drawable.intro_joy
                    Emotion.SADNESS -> R.drawable.intro_sad
                    Emotion.ANGER -> R.drawable.intro_anger
                    Emotion.DISGUST -> R.drawable.intro_disgust
                    Emotion.FEAR -> R.drawable.intro_fear
                }
            ),
            text = stringResource(
                id = when (uiState.emotion) {
                    Emotion.JOY -> R.string.description_joy
                    Emotion.SADNESS -> R.string.description_sad
                    Emotion.ANGER -> R.string.description_anger
                    Emotion.DISGUST -> R.string.description_sad
                    Emotion.FEAR -> R.string.description_sad
                }
            )
        )
    }
}
