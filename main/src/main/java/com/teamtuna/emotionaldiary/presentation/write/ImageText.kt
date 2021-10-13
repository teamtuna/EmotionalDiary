package com.teamtuna.emotionaldiary.presentation.write

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun ImageText(painter: Painter, text: String, modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier
                .width(18.dp)
                .height(18.dp)
        )
        Text(
            text = text,
            style = typography.h6,
            modifier = Modifier.padding(start = 6.dp)
        )
    }
}
