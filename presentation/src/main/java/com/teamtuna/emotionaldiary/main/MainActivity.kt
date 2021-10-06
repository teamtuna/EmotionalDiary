package com.teamtuna.emotionaldiary.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.teamtuna.emotionaldiary.compose.theme.EmotionalDiaryTheme
import com.teamtuna.emotionaldiary.navigation.AppNavigationGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScene()
        }
    }

    @Composable
    fun MainScene() {
        EmotionalDiaryTheme {
            ProvideWindowInsets {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomMenuComponent(navController)
                    }
                ) {
                    AppNavigationGraph(navController)
                }
            }
        }
    }
}

@Composable
fun BottomMenuComponent(navController: NavHostController, viewModel: MainViewModel = viewModel()) {
    val selectedMenu = viewModel.selectedMenu
    Row(
        Modifier
            .fillMaxSize(),
        horizontalArrangement = SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        TextButton(onClick = { viewModel.onSelectBottomMenu(BottomMenu.CALENDAR) }) {
            val color =
                if (selectedMenu.value == BottomMenu.CALENDAR) Color.Blue else Color.DarkGray
            Text(text = "달력", color = color)
        }
        TextButton(onClick = { viewModel.onSelectBottomMenu(BottomMenu.TIMELINE) }) {
            val color =
                if (selectedMenu.value == BottomMenu.TIMELINE) Color.Blue else Color.DarkGray
            Text(text = "타임라인", color = color)
        }
        TextButton(onClick = { viewModel.onSelectBottomMenu(BottomMenu.ANALYSIS) }) {
            val color =
                if (selectedMenu.value == BottomMenu.ANALYSIS) Color.Blue else Color.DarkGray
            Text(text = "분석", color = color)
        }
        TextButton(onClick = {
            viewModel.onSelectBottomMenu(BottomMenu.SETTING)
            navController.navigate(BottomMenu.SETTING.name)
        }) {
            val color =
                if (selectedMenu.value == BottomMenu.SETTING) Color.Blue else Color.DarkGray
            Text(text = "설정", color = color)
        }
    }
}
