package com.teamtuna.emotionaldiary.write

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.EditText
import android.widget.LinearLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.widget.addTextChangedListener
import com.teamtuna.emotionaldiary.presentation.R

@Composable
fun WriteTextField(modifier: Modifier = Modifier, contentChanged: (String) -> Unit) {
    AndroidView(
        factory = { context ->
            EditText(context).apply {
                layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                setBackgroundResource(android.R.color.transparent)
                setHint(R.string.write_diary_hint)
                minLines = 4
                maxLines = 8
                addTextChangedListener(afterTextChanged = {
                    if (it != null) {
                        contentChanged(it.toString())
                    }
                })
                requestFocus()
            }
        },
        modifier = modifier
    )
}
