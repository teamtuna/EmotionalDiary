package com.teamtuna.emotionaldiary.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

class TimelineFragment : Fragment() {

    private val viewModel: TimelineViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)
            )

            setContent {
                MaterialTheme {
                    MyApp {
                        Toast.makeText(requireContext(), "show detail", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    @Composable
    private fun MyApp(navigateToDetail: (EmotionItem) -> Unit) {
        Scaffold(
            content = {
                TimeLineContent(navigateToDetail)
            }
        )
    }

    @Composable
    private fun TimeLineContent(navigateToDetail: (EmotionItem) -> Unit) {
        val timeLine = remember { DataProvider.diaryList }

        LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
            items(
                items = timeLine,
                itemContent = {
                    TimeLineListItem(diary = it, navigateToDetail = navigateToDetail)
                }
            )
        }
    }

    @Composable
    private fun TimeLineListItem(diary: EmotionItem, navigateToDetail: (EmotionItem) -> Unit) {
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            elevation = 2.dp,
            shape = RoundedCornerShape(corner = CornerSize(16.dp))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                DiaryImage(diary)
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                        .clickable { navigateToDetail(diary) }
                ) {
                    Text(text = diary.emotion.toString(), style = MaterialTheme.typography.h5)
                    Text(text = diary.diaryContent)
                }
            }
        }
    }

    @ExperimentalCoilApi
    @Composable
    private fun DiaryImage(diary: EmotionItem) {
        Image(
            painter = rememberImagePainter(data = diary.imageUrl, builder = {
                transformations(CircleCropTransformation())
            }), contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
    }
}
