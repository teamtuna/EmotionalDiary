package com.teamtuna.emotionaldiary.write

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.teamtuna.emotionaldiary.KEY_EMOTION
import com.teamtuna.emotionaldiary.entity.Emotion
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriteActivity : AppCompatActivity() {

    private val viewModel: WriteViewModel by viewModels()

    companion object {
        fun newIntent(context: Context, emotion: Emotion) {
            Intent(context, WriteActivity::class.java)
                .apply {
                    putExtra(KEY_EMOTION, emotion.name)
                }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            WriteCompose()
        }
    }
}
