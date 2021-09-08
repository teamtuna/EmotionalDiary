package com.teamtuna.emotionaldiary.write

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun WriteTextField(state: MutableState<TextFieldValue>) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        BasicTextField(
            value = state.value,
            onValueChange = { state.value = it },
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        )
    }
}
