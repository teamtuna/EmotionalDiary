package com.teamtuna.emotionaldiary.add

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.teamtuna.emotionaldiary.entity.Emotion
import com.teamtuna.emotionaldiary.presentation.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmotionAddFragment : Fragment() {

    private val emotionAddViewModel: EmotionAddViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_emotion_add, container, false
        )

        emotionAddViewModel.response.observe(viewLifecycleOwner, {
            Log.e("TEST!!!", "response : $it")
        })

        view.findViewById<Button>(R.id.btn).setOnClickListener {
            emotionAddViewModel.add(Emotion.ANGER, "just")
        }

        return view
    }
}
