package com.teamtuna.emotionaldiary.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.teamtuna.emotionaldiary.main.R
import com.teamtuna.emotionaldiary.presentation.navigation.BottomNavGraph
import com.teamtuna.emotionaldiary.presentation.navigation.writeNavigate

@Composable
fun MainScaffold(
    mainNavHostController: NavHostController,
) {
    val bottomNavHostController: NavHostController = rememberNavController()
    Scaffold(
        topBar = {
            MainTopAppBar(
                title = stringResource(R.string.app_name),
                homeNavigate = { writeNavigate(mainNavHostController) }
            )
        },
        bottomBar = { MainBottomMenu(bottomNavHostController) },
        content = {
            BottomNavGraph(
                modifier = Modifier.padding(it),
                mainNavHostController = mainNavHostController,
                bottomNavHostController = bottomNavHostController
            )
        }
    )
}
