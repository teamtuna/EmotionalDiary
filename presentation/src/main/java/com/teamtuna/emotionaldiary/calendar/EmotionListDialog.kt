package com.teamtuna.emotionaldiary.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import be.sigmadelta.calpose.model.CalposeDate
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.util.icon

typealias OnEmotionClickedListener = (CalposeDate, Emotion) -> Unit

@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun EmotionListDialog(
    showDialog: Boolean,
    setShowDialog: (Boolean) -> Unit,
    currentDate: CalposeDate,
    onDismiss: () -> Unit,
    onEmotionClick: OnEmotionClickedListener,
) {
    if (!showDialog || currentDate.day == -1)
        return

    Dialog(onDismissRequest = onDismiss) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {

                Text(
                    text = "Select Emotion",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                LazyVerticalGrid(
                    cells = GridCells.Adaptive(96.dp),
                    content = {
                        Emotion.values().forEach { emotion ->
                            item {
                                IconButton(
                                    onClick = {
                                        onEmotionClick(currentDate, emotion)
                                        setShowDialog(false)
                                    },
                                    modifier = Modifier.padding(4.dp),
                                ) {
                                    Image(
                                        painter = rememberImagePainter(
                                            data = emotion.icon,
                                        ),
                                        contentScale = ContentScale.Inside,
                                        contentDescription = emotion.name,
                                    )
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}
