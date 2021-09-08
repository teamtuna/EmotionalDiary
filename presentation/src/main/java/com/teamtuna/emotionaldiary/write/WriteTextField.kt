package com.teamtuna.emotionaldiary.write

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun WriteTextField(state: MutableState<TextFieldValue>, scrollableState: ScrollableState) {
    OutlinedTextField(
        value = state.value,
        onValueChange = { state.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .scrollable(state = scrollableState, orientation = Orientation.Vertical)
    )
}
