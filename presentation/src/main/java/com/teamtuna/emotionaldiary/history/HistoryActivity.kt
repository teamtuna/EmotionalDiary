package com.teamtuna.emotionaldiary.history

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.teamtuna.emotionaldiary.presentation.R
import com.teamtuna.emotionaldiary.presentation.databinding.ActivityHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryActivity : AppCompatActivity() {

    private val viewModel: HistoryViewModel by viewModels()

    private lateinit var dataBinding: ActivityHistoryBinding

    private val historyAdapter by lazy {
        HistoryAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_history)

        viewModel.emotionList.observe(this@HistoryActivity, Observer {
            historyAdapter.submitList(it)
        })
    }
}
